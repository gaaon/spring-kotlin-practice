package practice.repository.comment.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("comment")
class CoffeeCommentDocument(
    @Id var id: ObjectId? = null,
    val nickname: String,
    val content: String,
    val rating: Int,
    val coffeeId: Long,
    val userId: Long,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CoffeeCommentDocument) return false

        if (id != other.id) return false
        if (nickname != other.nickname) return false
        if (content != other.content) return false
        if (rating != other.rating) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nickname.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + rating
        return result
    }

    override fun toString(): String {
        return "CommentDocument(id=$id, nickname='$nickname', content='$content', rating=$rating)"
    }
}
