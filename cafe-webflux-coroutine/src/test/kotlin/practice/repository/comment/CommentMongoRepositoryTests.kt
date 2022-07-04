package practice.repository.comment

import io.kotest.common.runBlocking
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
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
class CommentMongoRepositoryTests {
    companion object {
        @DynamicPropertySource
        @JvmStatic
        fun registerMongodbProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { SharedMongoTestContainer.getReplicaSetUrl() }
            registry.add("spring.data.mongodb.database") { "cafe" }
        }
    }

    @Autowired
    private lateinit var mongoTemplate: ReactiveMongoTemplate

    @Autowired
    private lateinit var commentMongoRepository: CommentMongoRepository

    @AfterEach
    fun tearDown(): Unit = runBlocking {
        mongoTemplate.dropCollection("comment").awaitSingleOrNull()
    }

    @Test
    fun contextLoad() {
        mongoTemplate shouldNotBe null
        commentMongoRepository shouldNotBe null
    }

    @Nested
    inner class FindAllByCoffeeId {
        private val commentSize = 10
        private var randomCoffeeId by Delegates.notNull<Long>()
        private lateinit var coffeeComments: List<CoffeeCommentDocument>

        @BeforeEach
        fun setUp(): Unit = runBlocking {
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

        @Test
        fun `should return commentSize-length comments`(): Unit = runBlocking {
            // when
            val comments = commentMongoRepository.findAllByCoffeeId(randomCoffeeId)
                .toList()

            // then
            comments shouldHaveSize commentSize
            comments.all { it.coffeeId == randomCoffeeId } shouldBe true
        }

        @Test
        fun `should return empty comment if other coffeeId is given`(): Unit = runBlocking {
            // given
            var otherCoffeeId = TestHelper.randomOtherLongId(randomCoffeeId)

            // when
            val comments = commentMongoRepository.findAllByCoffeeId(otherCoffeeId)
                .toList()

            // then
            comments shouldHaveSize 0
        }
    }
}
