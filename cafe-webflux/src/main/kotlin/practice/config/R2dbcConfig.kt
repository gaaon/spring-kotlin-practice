package practice.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@EnableR2dbcRepositories(
    basePackages = [
        "practice.repository.coffee",
    ]
)
@Configuration
class R2dbcConfig
