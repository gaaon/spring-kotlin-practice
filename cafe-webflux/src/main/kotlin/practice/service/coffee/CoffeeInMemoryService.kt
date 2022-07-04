package practice.service.coffee

import org.springframework.stereotype.Service
import practice.controller.coffee.model.CoffeeModel
import practice.service.coffee.domain.Coffee
import practice.service.coffee.domain.CoffeeComment
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CoffeeInMemoryService : CoffeeService {
    private val coffeeCommentMap = mapOf(
        1L to listOf(
            CoffeeComment(
                nickname = "유저1",
                content = "너무 시원해요",
                rating = 5,
            ),
            CoffeeComment(
                nickname = "유저2",
                content = "써요",
                rating = 1,
            ),
            CoffeeComment(
                nickname = "유저3",
                content = "가격이 비싸요",
                rating = 3,
            ),
            CoffeeComment(
                nickname = "유저4",
                content = "얼음이 너무 많아요",
                rating = 2,
            ),
        )
    )

    private val coffeeStockMap = mapOf(
        1L to 10L,
        2L to 100L,
        3L to 50L,
    )

    private val coffees = listOf(
        Coffee(
            id = 1,
            thumbnailUrl = "https://www.paris.co.kr/wp-content/uploads/211001_%EB%B9%85%EC%95%84%EC%9D%B4%EC%8A%A4%EC%95%84%EB%A9%94%EB%A6%AC%EC%B9%B4%EB%85%B8-1280.jpg",
            name = "아이스 아메리카노",
            description = "아메리카노 (아이스)",
            price = 2000,
        ),
        Coffee(
            id = 2,
            thumbnailUrl = "https://www.paris.co.kr/wp-content/uploads/%E1%84%8B%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%89%E1%85%B3%E1%84%85%E1%85%A1%E1%84%84%E1%85%A6-1280x1280.jpg",
            name = "아이스 라떼",
            description = "라떼 (아이스)",
            price = 3000,
        ),
        Coffee(
            id = 3,
            thumbnailUrl = "https://cdn.imweb.me/upload/S201904245cbfeaeb57b7d/4e9c648266bc4.jpg",
            name = "콜드브루",
            description = "콜드브루",
            price = 2500,
        ),
    )

    override fun findAll(): Flux<CoffeeModel> {
        return Flux.fromIterable(coffees)
            .map { coffee ->
                val comments = coffeeCommentMap[coffee.id] ?: emptyList()
                val rating = comments.map { it.rating }.average().let {
                    if (it.isNaN()) {
                        0.0
                    } else it
                }

                val stock = coffeeStockMap[coffee.id] ?: 0L
                CoffeeMapper.INSTANCE.toCoffeeModel(coffee, rating, comments, comments.size, stock)
            }
    }

    override fun findById(id: Long): Mono<CoffeeModel> {
        return Mono.justOrEmpty(
            coffees.find { it.id == id }?.let { coffee ->
                val comments = coffeeCommentMap[coffee.id] ?: emptyList()
                val rating = comments.map { it.rating }.average().let {
                    if (it.isNaN()) {
                        0.0
                    } else it
                }

                val stock = coffeeStockMap[coffee.id] ?: 0L
                CoffeeMapper.INSTANCE.toCoffeeModel(coffee, rating, comments, comments.size, stock)
            }
        )
    }
}
