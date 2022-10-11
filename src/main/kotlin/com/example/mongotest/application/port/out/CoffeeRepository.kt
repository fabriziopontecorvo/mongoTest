package com.example.mongotest.application.port.out

import com.example.mongotest.application.model.CoffeeDomain
import com.example.mongotest.rest.model.TypeCoffee
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface CoffeeRepository: MongoRepository<CoffeeDomain, UUID> {
    fun insert(coffee: CoffeeDomain): Any
    fun findAllByType(type: TypeCoffee): List<CoffeeDomain>
    fun findAllByBarista(barista: String): List<CoffeeDomain>
    fun findAllByClient(client: String): List<CoffeeDomain>
}
