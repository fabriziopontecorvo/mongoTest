package com.example.mongotest.application.port.`in`

import com.example.mongotest.application.model.CoffeeDomain
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

interface AsyncCoffeeInputPort {
    suspend fun asyncCreateCoffee(serverRequest: ServerRequest): ServerResponse
    suspend fun asyncObtainCoffees(serverRequest: ServerRequest): ServerResponse
    suspend fun asyncObtainCoffeesByType(serverRequest: ServerRequest): ServerResponse
    suspend fun asyncObtainCoffeesByBarista(serverRequest: ServerRequest): ServerResponse
    suspend fun asyncObtainCoffeesByClient(serverRequest: ServerRequest): ServerResponse
    suspend fun asyncDeleteById(serverRequest: ServerRequest): ServerResponse
}

