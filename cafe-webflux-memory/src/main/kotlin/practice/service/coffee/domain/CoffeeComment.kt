package practice.service.coffee.domain

data class CoffeeComment(
    val nickname: String,
    val content: String,
    val rating: Int,
    val avatarUrl: String,
)
