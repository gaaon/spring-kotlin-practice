package practice.controller.order

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import practice.controller.order.model.AddOrderRequest
import practice.controller.order.model.AddOrdersResponse
import practice.service.order.OrderInMemoryService
import practice.service.order.domain.CreateCoffeeItem
import practice.service.order.domain.CreateCoffeeOrder
import reactor.core.publisher.Mono

@RequestMapping("/api/orders")
@RestController
class OrderController(
    private val orderService: OrderInMemoryService,
) {
    @PostMapping
    fun addOrders(
        @RequestBody body: AddOrderRequest,
    ): Mono<AddOrdersResponse> {
        val req = CreateCoffeeOrder(
            items = body.items.map {
                CreateCoffeeItem(it.targetCoffeeId, it.quantity)
            }
        )

        return orderService.createOrder(req)
            .map { AddOrdersResponse(1) }
    }
}
