package practice.service.coffee

import org.springframework.context.annotation.Primary
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.stereotype.Service
import practice.cafe.user.rpc.ReactorCafeUserServiceGrpc
import practice.cafe.user.rpc.listCafeUsersReq
import practice.controller.coffee.model.CoffeeCommentModel
import practice.controller.coffee.model.CoffeeModel
import practice.repository.coffee.CoffeeR2dbcRepository
import practice.repository.coffee.record.CoffeeRecord
import practice.repository.comment.CommentMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Primary
@Service
class CoffeeServiceImpl(
    private val coffeeRepository: CoffeeR2dbcRepository,
    private val redisTemplate: ReactiveStringRedisTemplate,
    private val commentRepository: CommentMongoRepository,
    private val reactorCafeUserService: ReactorCafeUserServiceGrpc.ReactorCafeUserServiceStub,
) : CoffeeService {
    override fun findAll(): Flux<CoffeeModel> {
        return coffeeRepository.findAllByOrderById()
            .flatMap(this::addExtra)
    }

    override fun findById(id: Long): Mono<CoffeeModel> {
        return coffeeRepository.findById(id)
            .flatMap(this::addExtra)
    }

    private fun addExtra(coffee: CoffeeRecord): Mono<CoffeeModel> {
        val coffeeId = coffee.id

        return commentRepository.findAllByCoffeeId(coffeeId)
            .collectList()
            .flatMap { comments ->
                val rating = comments.map { it.rating }.average().let {
                    if (it.isNaN()) {
                        0.0
                    } else it
                }

                val commentUserIds = comments.map { it.userId }
                val req = listCafeUsersReq {
                    userIds.addAll(commentUserIds)
                }
                reactorCafeUserService.listCafeUsers(req)
                    .flatMap { res ->
                        val users = res.usersList.associateBy { it.id }

                        val commentModels = comments.mapNotNull {
                            users[it.userId]?.let { cafeUser ->
                                CoffeeCommentModel(
                                    nickname = it.nickname,
                                    content = it.content,
                                    rating = it.rating,
                                    avatarUrl = cafeUser.avatarUrl,
                                )
                            }
                        }

                        redisTemplate.opsForValue()["coffee:${coffeeId}:stock"]
                            .defaultIfEmpty("0")
                            .map { stock ->
                                CoffeeMapper.INSTANCE.toCoffeeModel2(
                                    coffee,
                                    rating,
                                    commentModels,
                                    comments.size,
                                    stock.toLong()
                                )
                            }
                    }
            }
    }
}
