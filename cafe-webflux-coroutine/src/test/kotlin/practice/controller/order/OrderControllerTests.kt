package practice.controller.order

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import practice.TestHelper
import practice.service.order.OrderService
import practice.service.order.domain.CreateCoffeeItem
import practice.service.order.domain.CreateCoffeeOrder
import kotlin.properties.Delegates

@Import(OrderControllerTestConfig::class)
@WebFluxTest(controllers = [OrderController::class])
class OrderControllerTests(
    private val webTestClient: WebTestClient,
    private val mockOrderService: OrderService,
) : DescribeSpec({
    describe("POST /api/orders") {
        context("items가 비어있다면") {
            it("304 status를 반환해야 한다") {
                // when
                val result = webTestClient.post()
                    .uri("/api/orders")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue("""
                        {
                            "items": []
                        }
                    """.trimIndent())
                    .exchange()
                    .returnResult<String>()

                // then
                result.status shouldBe HttpStatus.NOT_MODIFIED
            }
        }

        context("orderService가 exception을 throw하면") {
            lateinit var randomExceptionMsg: String
            var randomCoffeeId by Delegates.notNull<Long>()
            var randomQuantity by Delegates.notNull<Long>()

            beforeTest {
                randomExceptionMsg = TestHelper.randomSentence()
                randomCoffeeId = TestHelper.randomLongId()
                randomQuantity = TestHelper.randomQuantity()

                coEvery {
                    mockOrderService.createOrder(any())
                } throws IllegalStateException(randomExceptionMsg)
            }

            it("400 status를 반환해야 한다") {
                // when
                val result = webTestClient.post()
                    .uri("/api/orders")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue("""
                        {
                            "items": [
                                {
                                    "targetCoffeeId": $randomCoffeeId,
                                    "quantity": $randomQuantity
                                }
                            ]
                        }
                    """.trimIndent())
                    .exchange()
                    .returnResult<String>()

                // then
                result.status shouldBe HttpStatus.BAD_REQUEST

                coVerify(exactly = 1) {
                    mockOrderService.createOrder(CreateCoffeeOrder(
                        items = listOf(CreateCoffeeItem(
                            coffeeId = randomCoffeeId,
                            quantity = randomQuantity,
                        ))
                    ))
                }
            }
        }

        context("orderService에서 createOrder를 성공하면") {
            var randomCoffeeId by Delegates.notNull<Long>()
            var randomQuantity by Delegates.notNull<Long>()

            beforeTest {
                randomCoffeeId = TestHelper.randomLongId()
                randomQuantity = TestHelper.randomQuantity()

                coJustRun {
                    mockOrderService.createOrder(any())
                }
            }

            it("201 status와 body를 반환해야 한다") {
                // when
                val result = webTestClient.post()
                    .uri("/api/orders")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue("""
                        {
                            "items": [
                                {
                                    "targetCoffeeId": $randomCoffeeId,
                                    "quantity": $randomQuantity
                                }
                            ]
                        }
                    """.trimIndent())
                    .exchange()
                    .returnResult<String>()

                // then
                result.status shouldBe HttpStatus.CREATED

                coVerify(exactly = 1) {
                    mockOrderService.createOrder(CreateCoffeeOrder(
                        items = listOf(CreateCoffeeItem(
                            coffeeId = randomCoffeeId,
                            quantity = randomQuantity,
                        ))
                    ))
                }
            }
        }
    }
})
