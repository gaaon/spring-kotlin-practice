package practice.common.redis

import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import practice.TestHelper
import practice.repository.SharedRedisTestContainer
import kotlin.properties.Delegates
import kotlin.random.Random

@Import(MockConfig::class)
@SpringBootTest
class ReactiveStringRedisTemplateTests2(
    private val reactiveStringRedisTemplate: ReactiveStringRedisTemplate,
) : DescribeSpec() {
    companion object {
        @DynamicPropertySource
        @JvmStatic
        fun registerRedisProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.redis.host") { SharedRedisTestContainer.getHost() }
            registry.add("spring.redis.port") { SharedRedisTestContainer.getPort()}
        }
    }

    override fun extensions(): List<Extension> = listOf(SpringExtension)

    init {
        describe("increaseStock") {
            var randomCoffeeId by Delegates.notNull<Long>()
            var randomStock by Delegates.notNull<Long>()

            beforeTest {
                randomCoffeeId = TestHelper.randomLongId()
                randomStock = TestHelper.randomStock().toLong()

                reactiveStringRedisTemplate.opsForValue()
                    .set("coffee:$randomCoffeeId:stock", randomStock.toString())
                    .awaitSingle()
            }

            it("should return increased value after increasing") {
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

        describe("decreaseStock") {
            var randomCoffeeId by Delegates.notNull<Long>()
            var randomStock by Delegates.notNull<Long>()

            beforeTest {
                randomCoffeeId = TestHelper.randomLongId()
                randomStock = TestHelper.randomStock().toLong()

                reactiveStringRedisTemplate.opsForValue()
                    .set("coffee:$randomCoffeeId:stock", randomStock.toString())
                    .awaitSingle()
            }

            it("should return decreased value after decreasing") {
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
}
