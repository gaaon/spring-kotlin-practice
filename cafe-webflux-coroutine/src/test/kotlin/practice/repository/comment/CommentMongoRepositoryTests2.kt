package practice.repository.comment

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.context.annotation.Import
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import practice.TestHelper
import practice.repository.SharedMongoTestContainer
import practice.repository.comment.document.CoffeeCommentDocument
import kotlin.properties.Delegates
import kotlin.random.Random

@Import(CommentMongoRepositoryTestConfig::class)
@DataMongoTest
class CommentMongoRepositoryTests2(
    private val mongoTemplate: ReactiveMongoTemplate,
    private val commentMongoRepository: CommentMongoRepository,
) : DescribeSpec() {
    companion object {
        @DynamicPropertySource
        @JvmStatic
        fun registerMongodbProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { SharedMongoTestContainer.getReplicaSetUrl() }
            registry.add("spring.data.mongodb.database") { "cafe" }
        }
    }

    init {
        afterTest {
            mongoTemplate.dropCollection("comment").awaitSingleOrNull()
        }

        it("should load context") {
            mongoTemplate shouldNotBe null
            commentMongoRepository shouldNotBe null
        }

        describe("FindAllByCoffeeId") {
            val commentSize = 10
            var randomCoffeeId by Delegates.notNull<Long>()
            lateinit var coffeeComments: List<CoffeeCommentDocument>

            beforeTest {
                randomCoffeeId = TestHelper.randomLongId()
                coffeeComments = (0 until commentSize).map {
                    CoffeeCommentDocument(
                        id = null,
                        nickname = TestHelper.randomNickname(),
                        content = TestHelper.randomSentence(),
                        rating = Random.nextInt(1, 6),
                        coffeeId = randomCoffeeId,
                        userId = TestHelper.randomLongId(),
                    )
                }

                coffeeComments = mongoTemplate.insertAll(coffeeComments)
                    .collectList()
                    .awaitSingle()
            }

            it("should return commentSize-length comments") {
                // when
                val comments = commentMongoRepository.findAllByCoffeeId(randomCoffeeId)
                    .toList()

                // then
                comments shouldHaveSize commentSize
                comments.all { it.coffeeId == randomCoffeeId } shouldBe true
            }

            context("if other coffeeId is given") {
                var otherCoffeeId by Delegates.notNull<Long>()

                beforeTest {
                    otherCoffeeId = TestHelper.randomOtherLongId(randomCoffeeId)
                }

                it("should return empty comment") {
                    // when
                    val comments = commentMongoRepository.findAllByCoffeeId(otherCoffeeId)
                        .toList()

                    // then
                    comments shouldHaveSize 0
                }
            }
        }
    }
}
