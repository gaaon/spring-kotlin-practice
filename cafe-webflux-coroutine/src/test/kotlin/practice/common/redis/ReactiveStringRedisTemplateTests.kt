package practice.common.redis

import io.kotest.common.runBlocking
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.reactor.awaitSingle
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import practice.TestHelper
import kotlin.properties.Delegates
import kotlin.random.Random

@Import(MockConfig::class)
@SpringBootTest
@Testcontainers
class ReactiveStringRedisTemplateTests {
    companion object {
        @Container
        private val redis: GenericContainer<*> = GenericContainer<Nothing>(
            DockerImageName.parse("redis:5.0.3-alpine")
        ).withExposedPorts(6379)

        @DynamicPropertySource
        @JvmStatic
        fun registerRedisProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.redis.host") { redis.host }
            registry.add("spring.redis.port") { redis.getMappedPort(6379)}
        }
    }

    @Autowired
    private lateinit var reactiveStringRedisTemplate: ReactiveStringRedisTemplate

    @Nested
    inner class IncreaseStock {
        private var randomCoffeeId by Delegates.notNull<Long>()
        private var randomStock by Delegates.notNull<Long>()

        @BeforeEach
        fun setUp(): Unit = runBlocking {
            randomCoffeeId = TestHelper.randomLongId()
            randomStock = TestHelper.randomStock().toLong()

            reactiveStringRedisTemplate.opsForValue()
                .set("coffee:$randomCoffeeId:stock", randomStock.toString())
                .awaitSingle()
        }

        @Test
        fun `should return increased value after increasing`(): Unit = runBlocking {
            // given
            val randomIncrease = TestHelper.randomQuantity()

            // when
            val result = reactiveStringRedisTemplate.opsForValue()
                .increment("coffee:$randomCoffeeId:stock", randomIncrease)
                .awaitSingle()

            // then
            result shouldBe randomStock + randomIncrease
        }
    }

    @Nested
    inner class DecreaseStock {
        private var randomCoffeeId by Delegates.notNull<Long>()
        private var randomStock by Delegates.notNull<Long>()

        @BeforeEach
        fun setUp(): Unit = runBlocking {
            randomCoffeeId = TestHelper.randomLongId()
            randomStock = TestHelper.randomStock().toLong()

            reactiveStringRedisTemplate.opsForValue()
                .set("coffee:$randomCoffeeId:stock", randomStock.toString())
                .awaitSingle()
        }

        @Test
        fun `should return decreased value after decreasing`(): Unit = runBlocking {
            // given
            val randomDecrease = Random.nextLong(1, randomStock)

            // when
            val result = reactiveStringRedisTemplate.opsForValue()
                .decrement("coffee:$randomCoffeeId:stock", randomDecrease)
                .awaitSingle()

            // then
            result shouldBe randomStock - randomDecrease
        }
    }
}
