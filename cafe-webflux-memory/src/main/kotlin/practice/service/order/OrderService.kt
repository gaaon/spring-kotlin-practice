package practice.service.order

import practice.service.order.domain.CoffeeOrder
import practice.service.order.domain.CreateCoffeeOrder
import reactor.core.publisher.Mono

interface OrderService {
    fun createOrder(req: CreateCoffeeOrder): Mono<CoffeeOrder>
}
