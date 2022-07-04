package practice.controller.order

import com.ninjasquad.springmockk.MockkBean
import org.springframework.boot.test.context.TestConfiguration
import practice.service.order.OrderService

@TestConfiguration
class OrderControllerTestConfig {
    @MockkBean
    private lateinit var mockOrderService: OrderService
}
