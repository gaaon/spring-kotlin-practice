package practice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebConfig {
    @Bean
    fun telegramWebClient(
        @Value("\${TELEGRAM_BOT_TOKEN}") telegramBotToken: String,
    ): WebClient {
        return WebClient.builder()
            .baseUrl("https://api.telegram.org/bot$telegramBotToken")
            .build()
    }
}
