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

@RequestMapping("/api/orders")
@RestController
class OrderController(
    private val orderService: OrderService,
) {
    @PostMapping
    suspend fun addOrders(
        @RequestBody body: AddOrderRequest,
    ): AddOrdersResponse {
        val req = CreateCoffeeOrder(
            items = body.items.map {
                CreateCoffeeItem(it.targetCoffeeId, it.quantity)
            }
        )

        return try {
            orderService.createOrder(req)
            AddOrdersResponse(1)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message, e)
        }
    }
}
