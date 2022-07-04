package practice.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@EnableReactiveMongoRepositories(
    basePackages = [
        "practice.repository.comment",
        "practice.repository.order",
    ]
)
@Configuration
class MongoConfig
