package practice.repository.coffee.record

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("coffee")
class CoffeeRecord(
    @Id var id: Long? = null,
    val name: String,
    val price: Int,
    val thumbnailUrl: String,
    val description: String?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CoffeeRecord) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (price != other.price) return false
        if (thumbnailUrl != other.thumbnailUrl) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + price
        result = 31 * result + thumbnailUrl.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "CoffeeRecord(id=$id, name='$name', price=$price, thumbnailUrl='$thumbnailUrl', description=$description)"
    }
}
