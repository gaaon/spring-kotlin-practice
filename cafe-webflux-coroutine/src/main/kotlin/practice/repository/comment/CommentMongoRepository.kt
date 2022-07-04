package practice.repository.comment

import kotlinx.coroutines.flow.Flow
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineSortingRepository
import practice.repository.comment.document.CoffeeCommentDocument

interface CommentMongoRepository : CoroutineSortingRepository<CoffeeCommentDocument, ObjectId> {
    fun findAllByCoffeeId(coffeeId: Long): Flow<CoffeeCommentDocument>
}
