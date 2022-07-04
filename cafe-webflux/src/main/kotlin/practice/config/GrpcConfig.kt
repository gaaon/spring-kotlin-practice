package practice.config

import io.grpc.ManagedChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import practice.cafe.user.rpc.ReactorCafeUserServiceGrpc

@Configuration
class GrpcConfig {
    @Bean
    fun reactorCafeUserService(): ReactorCafeUserServiceGrpc.ReactorCafeUserServiceStub {
        val channel = ManagedChannelBuilder.forAddress("localhost", 6565)
            .usePlaintext()
            .build()

        return ReactorCafeUserServiceGrpc.newReactorStub(channel)
    }
}
