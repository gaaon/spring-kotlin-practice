package practice.repository.order

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import practice.repository.order.document.CoffeeOrderDocument

interface CoffeeOrderRepository : ReactiveMongoRepository<CoffeeOrderDocument, ObjectId>
