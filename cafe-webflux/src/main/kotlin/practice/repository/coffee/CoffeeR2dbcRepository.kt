package practice.repository.coffee

import org.springframework.data.repository.reactive.ReactiveSortingRepository
import practice.repository.coffee.record.CoffeeRecord
import reactor.core.publisher.Flux

interface CoffeeR2dbcRepository : ReactiveSortingRepository<CoffeeRecord, Long> {
    fun findAllByOrderById(): Flux<CoffeeRecord>
}
