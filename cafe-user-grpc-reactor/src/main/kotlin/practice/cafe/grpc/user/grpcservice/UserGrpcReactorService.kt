package practice.cafe.grpc.user.grpcservice

import org.lognet.springboot.grpc.GRpcService
import practice.cafe.grpc.user.grpcservice.model.CafeUser
import practice.cafe.user.rpc.GetCafeUserReq
import practice.cafe.user.rpc.GetCafeUserResp
import practice.cafe.user.rpc.ListCafeUsersReq
import practice.cafe.user.rpc.ListCafeUsersResp
import practice.cafe.user.rpc.ReactorCafeUserServiceGrpc
import practice.cafe.user.rpc.cafeUser
import practice.cafe.user.rpc.getCafeUserResp
import practice.cafe.user.rpc.listCafeUsersResp
import reactor.core.publisher.Mono

@GRpcService
class UserGrpcReactorService : ReactorCafeUserServiceGrpc.CafeUserServiceImplBase() {
    companion object {
        val userMap = mapOf(
            1L to CafeUser(
                id = 1L,
                nickname = "유저1",
                avatarUrl = "https://www.w3schools.com/howto/img_avatar.png"
            ),
            2L to CafeUser(
                id = 2L,
                nickname = "유저2",
                avatarUrl = "https://www.w3schools.com/howto/img_avatar2.png"
            ),
            3L to CafeUser(
                id = 3L,
                nickname = "유저3",
                avatarUrl = "https://www.w3schools.com/w3images/avatar2.png"
            ),
            4L to CafeUser(
                id = 4L,
                nickname = "유저4",
                avatarUrl = "https://www.w3schools.com/w3images/avatar6.png"
            ),
        )
    }

    override fun listCafeUsers(request: Mono<ListCafeUsersReq>): Mono<ListCafeUsersResp> {
        return request.map {
            val userIds = it.userIdsList

            val cafeUsers = userIds.mapNotNull { userId ->
                userMap[userId]?.let { user ->
                    cafeUser {
                        id = user.id
                        avatarUrl = user.avatarUrl
                        nickname = user.nickname
                    }
                }
            }

            listCafeUsersResp {
                users.addAll(cafeUsers)
            }
        }
    }

    override fun getCafeUser(request: Mono<GetCafeUserReq>): Mono<GetCafeUserResp> {
        return request.mapNotNull {
            val userId = it.userId

            userMap[userId]?.let { user ->
                getCafeUserResp {
                    this.user = cafeUser {
                        id = user.id
                        avatarUrl = user.avatarUrl
                        nickname = user.nickname
                    }
                }
            }
        }
    }
}
