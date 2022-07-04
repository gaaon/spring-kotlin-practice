package practice.service.order

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
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Primary
@Service
class OrderServiceImpl(
    private val redisTemplate: ReactiveStringRedisTemplate,
    private val orderRepository: CoffeeOrderRepository,
    private val streamBridge: StreamBridge,
) : OrderService {
    override fun createOrder(req: CreateCoffeeOrder): Mono<CoffeeOrder> {
        val stockDecreases = req.items.map { item ->
            redisTemplate.opsForValue()
                .decrement("coffee:${item.coffeeId}:stock", item.quantity)
                .map { res -> item.coffeeId to res }
        }

        return Flux.concat(stockDecreases)
            .collectList()
            .flatMap {
                val stockMap = it.toMap()

                val hasUnderStock = stockMap.any { (_, stock) ->
                    stock < 0
                }

                if (hasUnderStock) {
                    // should rollback
                    val stockRollbacks = req.items.map { item ->
                        redisTemplate.opsForValue()
                            .increment("coffee:${item.coffeeId}:stock", item.quantity)
                            .map { res -> item.coffeeId to res }
                    }

                    Flux.concat(stockRollbacks)
                        .collectList()
                        .map {
                            throw IllegalStateException("lack of stock")
                        }
                } else {
                    val coffeeOrder = CoffeeOrderDocument(
                        items = req.items.map { item ->
                            CoffeeOrderItemDocument(item.coffeeId, item.quantity)
                        }
                    )

                    orderRepository.save(coffeeOrder)
                        .doOnNext {
                            try {
                                streamBridge.send("orderSuccess-out-0", it.id?.toHexString())
                            } catch (e: Exception) {
                                println(e.message)
                            }
                        }
                }
            }.map {
                CoffeeOrder(it.id!!.toHexString(), it.items.map { item ->
                    CoffeeOrderItem(item.coffeeId, item.quantity)
                })
            }
    }
}
