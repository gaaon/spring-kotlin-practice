package practice.common.redis

import com.ninjasquad.springmockk.MockkBean
import org.springframework.boot.test.context.TestConfiguration
import practice.repository.coffee.CoffeeR2dbcRepository
import practice.repository.comment.CommentMongoRepository
import practice.repository.order.CoffeeOrderMongoRepository

@TestConfiguration
class MockConfig {
    @MockkBean
    private lateinit var mockCoffeeR2dbcRepository: CoffeeR2dbcRepository

    @MockkBean
    private lateinit var mockCommentMongoRepository: CommentMongoRepository

    @MockkBean
    private lateinit var mockkCoffeeOrderMongoRepository: CoffeeOrderMongoRepository
}
