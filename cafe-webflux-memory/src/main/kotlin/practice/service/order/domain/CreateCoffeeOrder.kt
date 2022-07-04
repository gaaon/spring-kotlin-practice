package practice.service.order.domain

data class CreateCoffeeItem(
    val coffeeId: Long,
    val quantity: Long,
)

data class CreateCoffeeOrder(
    val items: List<CreateCoffeeItem>,
)
