package practice.repository.comment

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@EnableReactiveMongoRepositories(
    basePackages = [
        "practice.repository.comment",
    ]
)
@TestConfiguration
class CommentMongoRepositoryTestConfig
