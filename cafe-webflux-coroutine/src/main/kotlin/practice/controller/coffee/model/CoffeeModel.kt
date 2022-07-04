package practice.controller.coffee.model

class CoffeeModel(
    val id: Long,
    val thumbnailUrl: String,
    val name: String,
    val stock: Long,
    val description: String,
    val price: Long,
    val comments: List<CoffeeCommentModel>,
    val rating: Double,
    val commentsCount: Long,
) {
    val priceStr: String
        get() {
            return "%,d".format(this.price)
        }
}
