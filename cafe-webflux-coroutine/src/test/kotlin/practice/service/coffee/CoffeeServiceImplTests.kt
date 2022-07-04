package practice.service.coffee

import io.kotest.core.spec.style.DescribeSpec
import io.mockk.mockk
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import practice.cafe.user.rpc.CafeUserServiceGrpcKt
import practice.repository.coffee.CoffeeR2dbcRepository
import practice.repository.comment.CommentMongoRepository

class CoffeeServiceImplTests : DescribeSpec({
    val mockCoffeeRepository = mockk<CoffeeR2dbcRepository>()
    val mockRedisTemplate = mockk<ReactiveStringRedisTemplate>()
    val mockCommentRepository = mockk< CommentMongoRepository>()
    val mockCafeUserService = mockk< CafeUserServiceGrpcKt.CafeUserServiceCoroutineStub>()

    val coffeeService = CoffeeServiceImpl(
        coffeeRepository = mockCoffeeRepository,
        redisTemplate = mockRedisTemplate,
        commentRepository = mockCommentRepository,
        cafeUserService = mockCafeUserService
    )

    describe("CoffeeService") {

    }
})
