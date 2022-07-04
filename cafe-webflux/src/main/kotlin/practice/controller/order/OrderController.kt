package practice.controller.order

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import practice.controller.order.model.AddOrderRequest
import practice.controller.order.model.AddOrdersResponse
import practice.service.order.OrderService
import practice.service.order.domain.CreateCoffeeItem
import practice.service.order.domain.CreateCoffeeOrder
import reactor.core.publisher.Mono

@RequestMapping("/api/orders")
@RestController
class OrderController(
    private val orderService: OrderService,
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
            .onErrorMap {
                ResponseStatusException(HttpStatus.BAD_REQUEST, it.message, it)
            }
    }
}
