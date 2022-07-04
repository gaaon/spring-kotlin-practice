package practice

import org.springframework.boot.actuate.autoconfigure.amqp.RabbitHealthContributorAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.tools.agent.ReactorDebugAgent

@SpringBootApplication(
    exclude = [
        RabbitHealthContributorAutoConfiguration::class,
    ]
)
class CafeWebfluxOnlyApplication

fun main(vararg args: String) {
    ReactorDebugAgent.init()
    runApplication<CafeWebfluxOnlyApplication>(*args)
}
