package practice.service.order

import practice.service.order.domain.CoffeeOrder
import practice.service.order.domain.CreateCoffeeOrder

interface OrderService {
    suspend fun createOrder(req: CreateCoffeeOrder): CoffeeOrder
}
