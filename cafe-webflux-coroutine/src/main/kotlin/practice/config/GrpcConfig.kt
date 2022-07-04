package practice.config

import io.grpc.ManagedChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import practice.cafe.user.rpc.CafeUserServiceGrpcKt

@Configuration
class GrpcConfig {
    @Bean
    fun coroutineCafeUserService(): CafeUserServiceGrpcKt.CafeUserServiceCoroutineStub {
        val channel = ManagedChannelBuilder.forAddress("localhost", 6565)
            .usePlaintext()
            .build()

        return CafeUserServiceGrpcKt.CafeUserServiceCoroutineStub(channel)
    }
}
