package practice.service.coffee

import practice.controller.coffee.model.CoffeeModel
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CoffeeService {
    fun findAll(): Flux<CoffeeModel>
    fun findById(id: Long): Mono<CoffeeModel>
}
