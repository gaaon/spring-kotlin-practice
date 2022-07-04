package practice.service.order

import org.springframework.stereotype.Service
import practice.service.order.domain.CoffeeOrder
import practice.service.order.domain.CoffeeOrderItem
import practice.service.order.domain.CreateCoffeeOrder
import reactor.core.publisher.Mono
import kotlin.random.Random

@Service
class OrderInMemoryService : OrderService {
    override fun createOrder(req: CreateCoffeeOrder): Mono<CoffeeOrder> {
        return Mono.just(
            CoffeeOrder(
                id = Random.nextLong(10000, 100000000000).toString(),
                items = req.items.map {
                    CoffeeOrderItem(it.coffeeId, it.quantity)
                }
            )
        )
    }
}
