package practice.controller.order

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    ): ResponseEntity<AddOrdersResponse> {
        val req = CreateCoffeeOrder(
            items = body.items.map {
                CreateCoffeeItem(it.targetCoffeeId, it.quantity)
            }
        )

        if (body.items.isEmpty()) {
            return ResponseEntity(HttpStatus.NOT_MODIFIED)
        }

        return try {
            orderService.createOrder(req)
            ResponseEntity(AddOrdersResponse(1), HttpStatus.CREATED)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message, e)
        }
    }
}
