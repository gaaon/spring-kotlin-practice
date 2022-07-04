package practice.repository.comment

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import practice.repository.comment.document.CoffeeCommentDocument
import reactor.core.publisher.Flux

interface CommentMongoRepository : ReactiveMongoRepository<CoffeeCommentDocument, ObjectId> {
    fun findAllByCoffeeId(coffeeId: Long): Flux<CoffeeCommentDocument>
}
