package practice.repository.coffee

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineSortingRepository
import practice.repository.coffee.record.CoffeeRecord

interface CoffeeR2dbcRepository : CoroutineSortingRepository<CoffeeRecord, Long> {
    fun findAllByOrderById(): Flow<CoffeeRecord>
}
