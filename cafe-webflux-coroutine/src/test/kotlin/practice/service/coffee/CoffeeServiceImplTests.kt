package practice.service.coffee

import io.kotest.assertions.asClue
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.toList
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import practice.TestHelper
import practice.cafe.user.rpc.CafeUserServiceGrpcKt
import practice.controller.coffee.model.CoffeeModel
import practice.repository.coffee.CoffeeR2dbcRepository
import practice.repository.coffee.record.CoffeeRecord
import practice.repository.comment.CommentMongoRepository
import kotlin.properties.Delegates

class CoffeeServiceImplTests : DescribeSpec({
    val mockCoffeeRepository = mockk<CoffeeR2dbcRepository>()
    val mockRedisTemplate = mockk<ReactiveStringRedisTemplate>()
    val mockCommentRepository = mockk<CommentMongoRepository>()
    val mockCafeUserService = mockk<CafeUserServiceGrpcKt.CafeUserServiceCoroutineStub>()

    val coffeeService = spyk(
        CoffeeServiceImpl(
            coffeeRepository = mockCoffeeRepository,
            redisTemplate = mockRedisTemplate,
            commentRepository = mockCommentRepository,
            cafeUserService = mockCafeUserService
        )
    )

    var randomCoffeeId by Delegates.notNull<Long>()

    beforeTest {
        randomCoffeeId = TestHelper.randomLongId()
    }

    afterTest {
        clearAllMocks()
    }

    describe("findById") {
        context("만약 repository가 null을 반환한다면") {
            beforeTest {
                coEvery {
                    mockCoffeeRepository.findById(randomCoffeeId)
                } returns null
            }

            it("null을 반환해야 한다") {
                // when
                val coffee = coffeeService.findById(randomCoffeeId)

                // then
                coffee shouldBe null

                coVerify(exactly = 1) {
                    mockCoffeeRepository.findById(randomCoffeeId)
                }

                coVerify(exactly = 0) {
                    coffeeService.addExtra(any())
                }
            }
        }

        context("만약 repository가 coffee를 반환한다면") {
            lateinit var mockCoffeeRecord: CoffeeRecord
            lateinit var mockCoffeeModel: CoffeeModel

            beforeTest {
                mockCoffeeRecord = CoffeeRecord(
                    id = randomCoffeeId,
                    name = TestHelper.randomCoffeeName(),
                    thumbnailUrl = TestHelper.randomImageUrl(),
                    price = TestHelper.randomPrice(),
                    description = TestHelper.randomSentence(),
                )

                mockCoffeeModel = mockk()

                coEvery {
                    mockCoffeeRepository.findById(randomCoffeeId)
                } returns mockCoffeeRecord

                coEvery {
                    coffeeService.addExtra(any())
                } returns mockCoffeeModel
            }

            it("coffeeModel을 반환해야 한다") {
                // when
                val coffee = coffeeService.findById(randomCoffeeId)

                // then
                coffee shouldBe mockCoffeeModel

                coVerify(exactly = 1) {
                    mockCoffeeRepository.findById(randomCoffeeId)
                }

                val coffeeSlot = slot<CoffeeRecord>()
                coVerify(exactly = 1) {
                    coffeeService.addExtra(capture(coffeeSlot))
                }

                coffeeSlot.captured.asClue {
                    it.id shouldBe randomCoffeeId
                    it.price shouldBeGreaterThan 0
                    it.description.isNullOrBlank() shouldBe false
                }
            }
        }
    }

    describe("findAll") {
        context("만약 repository가 텅 빈 flow를 반환한다면") {
            beforeTest {
                every {
                    mockCoffeeRepository.findAllByOrderById()
                } returns emptyFlow()
            }

            it("텅 빈 model flow를 반환해야 한다") {
                // when
                val coffeeModels = coffeeService.findAll().toList()

                // then
                coffeeModels shouldHaveSize 0

                coVerify(exactly = 1) {
                    mockCoffeeRepository.findAllByOrderById()
                }

                coVerify(exactly = 0) {
                    coffeeService.addExtraWithAsync(any())
                }
            }
        }

        context("만약 repository가 여러 coffee가 포함된 flow를 반환한다면") {
            val coffeeSize = 10
            lateinit var mockCoffeeRecords: List<CoffeeRecord>
            lateinit var mockCoffeeModels: List<CoffeeModel>
            lateinit var randomCoffeeIds: List<Long>

            beforeTest {
                randomCoffeeIds = (0 until coffeeSize).map { TestHelper.randomLongId() }
                mockCoffeeRecords =  randomCoffeeIds.map { coffeeId ->
                    CoffeeRecord(
                        id = coffeeId,
                        name = TestHelper.randomCoffeeName(),
                        thumbnailUrl = TestHelper.randomImageUrl(),
                        price = TestHelper.randomPrice(),
                        description = TestHelper.randomSentence(),
                    )
                }
                mockCoffeeModels = (0 until coffeeSize).map { mockk() }

                every {
                    mockCoffeeRepository.findAllByOrderById()
                } returns mockCoffeeRecords.asFlow()

                coEvery {
                    coffeeService.addExtraWithAsync(any())
                } returnsMany mockCoffeeModels
            }

            it("여러 coffeeModel이 포함된 flow를 반환해야 한다") {
                // when
                val coffeeModels = coffeeService.findAll().toList()

                // then
                coffeeModels shouldBe mockCoffeeModels

                coVerify(exactly = 1) {
                    mockCoffeeRepository.findAllByOrderById()
                }

                coVerify(exactly = coffeeSize) {
                    coffeeService.addExtraWithAsync(any())
                }
            }
        }
    }
})
