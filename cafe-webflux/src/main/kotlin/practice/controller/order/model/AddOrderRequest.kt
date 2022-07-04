package practice.controller.order.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.LowerCamelCaseStrategy::class)
data class AddOrderItem(
    val targetCoffeeId: Long = 0,
    val quantity: Long = 0,
)

@JsonNaming(value = PropertyNamingStrategies.LowerCamelCaseStrategy::class)
data class AddOrderRequest(
    val items: List<AddOrderItem> = emptyList(),
)
