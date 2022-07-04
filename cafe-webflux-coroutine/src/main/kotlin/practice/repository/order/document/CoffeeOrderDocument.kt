package practice.repository.order.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

data class CoffeeOrderItemDocument(
    val coffeeId: Long,
    val quantity: Long,
)

@Document("order")
class CoffeeOrderDocument(
    @Id var id: ObjectId? = null,
    val items: List<CoffeeOrderItemDocument>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CoffeeOrderDocument) return false

        if (id != other.id) return false
        if (items != other.items) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + items.hashCode()
        return result
    }

    override fun toString(): String {
        return "CoffeeOrderDocument(id=$id, items=$items)"
    }
}
