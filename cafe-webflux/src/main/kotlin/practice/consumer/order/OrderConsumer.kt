package practice.consumer.order

import org.bson.types.ObjectId
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import practice.repository.coffee.CoffeeR2dbcRepository
import practice.repository.order.CoffeeOrderRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Function

@Component
class OrderConsumer(
    private val orderRepository: CoffeeOrderRepository,
    private val coffeeRepository: CoffeeR2dbcRepository,
    private val telegramWebClient: WebClient,
) : Function<Flux<ByteArray>, Mono<Void>> {
    companion object {
        const val CHAT_ID = 5468792046L
    }

    private val log = LoggerFactory.getLogger(OrderConsumer::class.java)

    override fun apply(t: Flux<ByteArray>): Mono<Void> {
        return t.flatMap {
            val orderId = String(it)
            orderRepository.findById(ObjectId(orderId))
        }.flatMap { coffeeOrder ->
            val coffeeIds = coffeeOrder.items.map { it.coffeeId }

            coffeeRepository.findAllById(coffeeIds)
                .collectList()
                .flatMap { coffees ->
                    val coffeeIdMap = coffees.associateBy { it.id }

                    val chatMessage = "주문이 들어왔어요!\n" + coffeeOrder.items.mapNotNull {
                        coffeeIdMap[it.coffeeId]?.let { coffee ->
                            "${coffee.name}(id: ${it.coffeeId}): ${it.quantity}개"
                        }
                    }.joinToString("\n")

                    telegramWebClient.get()
                        .uri {
                            it.path("/sendMessage")
                                .queryParam("chat_id", CHAT_ID)
                                .queryParam("text", chatMessage)
                                .build()
                        }
                        .retrieve()
                        .bodyToMono(String::class.java)
                }
        }.doOnNext {
            println(it)
        }.onErrorContinue { t, _ ->
            log.error(t.message, t)
        }.then()
    }
}
