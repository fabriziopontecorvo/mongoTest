package com.example.mongotest.adapter.repository

import com.example.mongotest.application.model.CoffeeDomain
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AsyncCoffeeRepository : CoroutineCrudRepository<CoffeeDomain, String> {
   fun findByType(type: String): Flow<CoffeeDomain>
   fun findByBarista(barista: String): Flow<CoffeeDomain>
   fun findByClient(client: String): Flow<CoffeeDomain>
}

