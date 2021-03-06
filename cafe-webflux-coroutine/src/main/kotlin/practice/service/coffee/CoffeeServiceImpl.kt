package practice.service.coffee

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.withContext
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.stereotype.Service
import practice.cafe.user.rpc.CafeUserServiceGrpcKt
import practice.cafe.user.rpc.listCafeUsersReq
import practice.controller.coffee.model.CoffeeCommentModel
import practice.controller.coffee.model.CoffeeModel
import practice.repository.coffee.CoffeeR2dbcRepository
import practice.repository.coffee.record.CoffeeRecord
import practice.repository.comment.CommentMongoRepository

@Primary
@Service
class CoffeeServiceImpl(
    private val coffeeRepository: CoffeeR2dbcRepository,
    private val redisTemplate: ReactiveStringRedisTemplate,
    private val commentRepository: CommentMongoRepository,
    private val cafeUserService: CafeUserServiceGrpcKt.CafeUserServiceCoroutineStub,
) : CoffeeService {
    fun findAllAsMock(): Flow<CoffeeModel> {
        return flow {
            val models = listOf(
                CoffeeModel(
                    id = 100,
                    thumbnailUrl = "https://media-cldnry.s-nbcnews.com/image/upload/newscms/2019_33/2203981/171026-better-coffee-boost-se-329p.jpg",
                    name = "뜨거운 아메리카노",
                    stock = 100,
                    description = "아메리카노 (핫)",
                    price = 1300,
                    comments = emptyList(),
                    rating = 0.0,
                    commentsCount = 0,
                ),
                CoffeeModel(
                    id = 101,
                    thumbnailUrl = "https://media-cldnry.s-nbcnews.com/image/upload/newscms/2019_33/2203981/171026-better-coffee-boost-se-329p.jpg",
                    name = "뜨거운 아메리카노 (얼음 적게)",
                    stock = 100,
                    description = "아메리카노 (핫, 얼음 적게)",
                    price = 1100,
                    comments = emptyList(),
                    rating = 0.0,
                    commentsCount = 0,
                )
            )

            this.emitAll(models.asFlow())
        }
    }

    override fun findAll(): Flow<CoffeeModel> {
        return coffeeRepository.findAllByOrderById()
            .map { addExtraWithAsync(it) }
    }

    override suspend fun findById(id: Long): CoffeeModel? {
        return coffeeRepository.findById(id)
            ?.let { addExtra(it) }
    }

    internal suspend fun addExtra(coffee: CoffeeRecord): CoffeeModel {
        val coffeeId = coffee.id!!

        val comments = commentRepository.findAllByCoffeeId(coffeeId).toList()

        val rating = comments.map { it.rating }.average().let {
            it.takeIf { !it.isNaN() } ?: 0.0
        }

        val userIdMap = cafeUserService.listCafeUsers(
            listCafeUsersReq {
                userIds.addAll(comments.map { it.userId })
            })
            .usersList
            .associateBy { it.id }

        val commentModels = comments.mapNotNull {
            userIdMap[it.userId]?.let { cafeUser ->
                CoffeeCommentModel(
                    nickname = it.nickname,
                    content = it.content,
                    rating = it.rating,
                    avatarUrl = cafeUser.avatarUrl,
                )
            }
        }

        val stock = redisTemplate.opsForValue()["coffee:${coffeeId}:stock"]
            .defaultIfEmpty("0")
            .awaitSingle()
            .toLong()

        return CoffeeMapper.INSTANCE.toCoffeeModel(
            coffee,
            rating,
            commentModels,
            comments.size,
            stock
        )
    }

    @Suppress("UnusedPrivateMember")
    internal suspend fun addExtraWithAsync(coffee: CoffeeRecord): CoffeeModel {
        val coffeeId = coffee.id!!

        val (comments, stock) = withContext(Dispatchers.IO) {
            coroutineScope {
                val commentsDeferred = async {
                    commentRepository.findAllByCoffeeId(coffeeId).toList()
                }

                val stockDeferred = async {
                    redisTemplate.opsForValue()["coffee:${coffeeId}:stock"]
                        .defaultIfEmpty("0")
                        .awaitSingle()
                        .toLong()
                }

                commentsDeferred.await() to stockDeferred.await()
            }
        }

        val rating = comments.map { it.rating }.average().let {
            if (it.isNaN()) 0.0 else it
        }

        val userIdMap = cafeUserService.listCafeUsers(
            listCafeUsersReq {
                userIds.addAll(comments.map { it.userId })
            })
            .usersList
            .associateBy { it.id }

        val commentModels = comments.mapNotNull {
            userIdMap[it.userId]?.let { cafeUser ->
                CoffeeMapper.INSTANCE.toCoffeeCommentModel(it, cafeUser)
            }
        }

        return CoffeeMapper.INSTANCE.toCoffeeModel(
            coffee,
            rating,
            commentModels,
            comments.size,
            stock
        )
    }
}
