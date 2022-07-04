package practice.e2e

import com.ninjasquad.springmockk.MockkBean
import io.kotest.common.ExperimentalKotest
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.jsoup.Jsoup
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.delete
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import practice.TestHelper
import practice.cafe.user.rpc.CafeUser
import practice.cafe.user.rpc.CafeUserServiceGrpcKt
import practice.cafe.user.rpc.ListCafeUsersReq
import practice.cafe.user.rpc.cafeUser
import practice.cafe.user.rpc.listCafeUsersResp
import practice.repository.SharedMongoTestContainer
import practice.repository.SharedRedisTestContainer
import practice.repository.coffee.record.CoffeeRecord
import practice.repository.comment.document.CoffeeCommentDocument

@OptIn(ExperimentalKotest::class)
@ActiveProfiles("e2e")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CafeMenuE2eTests(
    private val webTestClient: WebTestClient,
    private val r2dbcEntityTemplate: R2dbcEntityTemplate,
    private val reactiveMongoTemplate: ReactiveMongoTemplate,
    private val reactiveStringRedisTemplate: ReactiveStringRedisTemplate,
) : DescribeSpec() {
    companion object {
        @DynamicPropertySource
        @JvmStatic
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { SharedMongoTestContainer.getReplicaSetUrl() }
            registry.add("spring.data.mongodb.database") { "cafe" }

            registry.add("spring.redis.host") { SharedRedisTestContainer.getHost() }
            registry.add("spring.redis.port") { SharedRedisTestContainer.getPort() }
        }
    }

    override fun extensions(): List<Extension> = listOf(SpringExtension)

    @MockkBean
    private lateinit var mockCafeUserService: CafeUserServiceGrpcKt.CafeUserServiceCoroutineStub

    init {
        afterTest {
            r2dbcEntityTemplate.delete<CoffeeRecord>().all().awaitSingleOrNull()
            reactiveMongoTemplate.dropCollection("comment").awaitSingleOrNull()
            reactiveStringRedisTemplate.delete("coffee:*").awaitSingleOrNull()
        }

        describe("커피 메뉴 페이지") {
            context("만약 mysql에 커피가 없다면") {
                it("텅 빈 메뉴 페이지를 반환해야 한다") {
                    // when
                    val resp = webTestClient.get()
                        .uri("/pages/menu")
                        .exchange()
                        .returnResult<String>()

                    val body = resp.responseBody
                        .collectList()
                        .awaitSingle()
                        .joinToString("\n")

                    // then
                    println(body)
                    val doc = Jsoup.parse(body)

                    val coffeeCountStr = doc.select(".total-coffee-count").text()
                    coffeeCountStr shouldBe "총 커피 수 : 0"

                    val coffeeCards = doc.select(".coffee-card-container > .card")
                    coffeeCards.size shouldBe 0
                }
            }

            context("만약 mysql에 커피들이 있다면") {
                val coffeeSize = 10
                val firstCoffeeCommentSize = 5
                lateinit var coffeeRecords: List<CoffeeRecord>
                lateinit var randomStocks: List<Long>
                lateinit var commentDocuments: List<CoffeeCommentDocument>

                beforeTest {
                    coffeeRecords = (0 until coffeeSize).map {
                        val record = CoffeeRecord(
                            id = null,
                            name = TestHelper.randomCoffeeName(),
                            thumbnailUrl = TestHelper.randomImageUrl(),
                            price = TestHelper.randomPrice(),
                            description = TestHelper.randomSentence(),
                        )
                        r2dbcEntityTemplate.insert(record).awaitSingle()
                    }

                    randomStocks = (0 until coffeeSize).map { TestHelper.randomQuantity() }
                    for ((idx, record) in coffeeRecords.withIndex()) {
                        reactiveStringRedisTemplate.opsForValue()
                            .set("coffee:${record.id}:stock", randomStocks[idx].toString())
                            .awaitSingle()
                    }

                    commentDocuments = (0 until firstCoffeeCommentSize).map {
                        val document = CoffeeCommentDocument(
                            id = null,
                            nickname = TestHelper.randomNickname(),
                            content = TestHelper.randomSentence(),
                            rating = TestHelper.randomRating(),
                            coffeeId = coffeeRecords[0].id!!,
                            userId = TestHelper.randomLongId(),
                        )

                        reactiveMongoTemplate.save(document).awaitSingle()
                    }

                    coEvery {
                        mockCafeUserService.listCafeUsers(any(), any())
                    } answers {
                        val req = this.arg<ListCafeUsersReq>(0)
                        val cafeUsers = req.userIdsList.map {
                            cafeUser {
                                id = it
                                nickname = ""
                                avatarUrl = TestHelper.randomImageUrl()
                            }
                        }

                        listCafeUsersResp {
                            users.addAll(cafeUsers)
                        }
                    }
                }

                it("커피 목록이 담긴 메뉴 페이지를 반환해야 한다") {
                    // when
                    val resp = webTestClient.get()
                        .uri("/pages/menu")
                        .exchange()
                        .returnResult<String>()

                    val body = resp.responseBody
                        .collectList()
                        .awaitSingle()
                        .joinToString("\n")

                    // then
                    println(body)
                    val doc = Jsoup.parse(body)

                    val coffeeCountStr = doc.select(".total-coffee-count").text()
                    coffeeCountStr shouldBe "총 커피 수 : $coffeeSize"

                    val coffeeCards = doc.select(".coffee-card-container > .card")
                    coffeeCards.size shouldBe coffeeSize

                    val stockStrs = coffeeCards.map { it.select(".card-text")[2].text() }
                    stockStrs.forEachIndexed { index, stockStr ->
                        stockStr shouldBe "재고: ${randomStocks[index]}개"
                    }
                }
            }
        }
    }
}
