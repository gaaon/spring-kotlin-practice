package practice.service.coffee

import kotlinx.coroutines.flow.Flow
import practice.controller.coffee.model.CoffeeModel

interface CoffeeService {
    fun findAll(): Flow<CoffeeModel>
    suspend fun findById(id: Long): CoffeeModel?
}
