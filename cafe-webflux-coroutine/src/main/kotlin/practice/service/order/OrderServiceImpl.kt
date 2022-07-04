package practice.service.order

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.stereotype.Service
import practice.repository.order.CoffeeOrderRepository
import practice.repository.order.document.CoffeeOrderDocument
import practice.repository.order.document.CoffeeOrderItemDocument
import practice.service.order.domain.CoffeeOrder
import practice.service.order.domain.CoffeeOrderItem
import practice.service.order.domain.CreateCoffeeOrder

@Primary
@Service
class OrderServiceImpl(
    private val redisTemplate: ReactiveStringRedisTemplate,
    private val orderRepository: CoffeeOrderRepository,
    private val streamBridge: StreamBridge,
) : OrderService {
    override suspend fun createOrder(req: CreateCoffeeOrder): CoffeeOrder {
        val stockDecreaseMap = coroutineScope {
            val stockDecreasesDeferred = req.items.map { item ->
                async {
                    redisTemplate.opsForValue()
                        .decrement("coffee:${item.coffeeId}:stock", item.quantity)
                        .map { res -> item.coffeeId to res }
                        .awaitSingle()
                }
            }

            stockDecreasesDeferred.awaitAll()
        }.toMap()

        val hasUnderStock = stockDecreaseMap.any { (_, stock) ->
            stock < 0
        }

        if (hasUnderStock) {
            // should rollback
            coroutineScope {
                val stockRollbacksDeferred = req.items.map { item ->
                    async {
                        redisTemplate.opsForValue()
                            .increment("coffee:${item.coffeeId}:stock", item.quantity)
                            .map { res -> item.coffeeId to res }
                            .awaitSingle()
                    }
                }

                stockRollbacksDeferred.awaitAll()
            }

            throw IllegalStateException("lack of stock")
        } else {
            val coffeeOrder = CoffeeOrderDocument(
                items = req.items.map { item ->
                    CoffeeOrderItemDocument(item.coffeeId, item.quantity)
                }
            )

            val createdOrder = orderRepository.save(coffeeOrder).awaitSingle()

            try {
                streamBridge.send("orderSuccess-out-0", createdOrder.id?.toHexString())
            } catch (e: Exception) {
                println(e.message)
            }

            return CoffeeOrder(createdOrder.id!!.toHexString(), createdOrder.items.map { item ->
                CoffeeOrderItem(item.coffeeId, item.quantity)
            })
        }
    }
}
