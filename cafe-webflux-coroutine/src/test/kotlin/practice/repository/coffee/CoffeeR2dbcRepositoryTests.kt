package practice.repository.coffee

import io.kotest.common.runBlocking
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.longs.shouldBeLessThan
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.delete
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import practice.TestHelper
import practice.repository.coffee.record.CoffeeRecord
import kotlin.random.Random

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [CoffeeR2dbcTestConfig::class])
class CoffeeR2dbcRepositoryTests {
    @Autowired
    private lateinit var r2dbcEntityTemplate: R2dbcEntityTemplate

    @Autowired
    private lateinit var coffeeR2dbcRepository: CoffeeR2dbcRepository

    @AfterEach
    fun tearDown(): Unit = runBlocking {
        r2dbcEntityTemplate.delete<CoffeeRecord>().all().awaitSingle()
    }

    @Test
    fun contextLoad() {
        r2dbcEntityTemplate shouldNotBe null
        coffeeR2dbcRepository shouldNotBe null
    }

    @Nested
    inner class FindAllByOrderById {
        private val coffeeSize = 10
        private lateinit var mockCoffeeRecords: List<CoffeeRecord>

        @Test
        fun `should return empty coffee`(): Unit = runBlocking {
            // when
            val coffees = coffeeR2dbcRepository.findAllByOrderById().toList()

            // then
            coffees shouldHaveSize 0
        }

        @Nested
        inner class AfterInsertingCoffees {
            @BeforeEach
            fun setUp() = runBlocking {
                mockCoffeeRecords = (0 until coffeeSize).map {
                    CoffeeRecord(
                        id = null,
                        name = TestHelper.randomCoffeeName(),
                        price = TestHelper.randomPrice(),
                        thumbnailUrl = TestHelper.randomImageUrl(),
                        description = TestHelper.randomSentence(),
                    )
                }

                for (record in mockCoffeeRecords) {
                    r2dbcEntityTemplate.insert(record).awaitSingle()
                }
            }

            @Test
            fun `should have coffeeSize-length coffee flow`(): Unit = runBlocking {
                // when
                val coffees = coffeeR2dbcRepository.findAllByOrderById().toList()

                // then
                coffees shouldHaveSize coffeeSize

                for (i in (1 until coffeeSize)) {
                    coffees[i - 1].id!! shouldBeLessThan coffees[i].id!!
                }
            }
        }
    }
}
