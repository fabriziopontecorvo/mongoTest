package com.example.mongotest.application.port.`in`

import com.example.mongotest.application.model.CoffeeDomain
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

interface CoffeeInputPort {
    fun createCoffee(serverRequest: ServerRequest): Mono<ServerResponse>
    suspend fun asyncCreateCoffee(serverRequest: ServerRequest): ServerResponse
    fun obtainCoffees(serverRequest: ServerRequest): Mono<ServerResponse>
    fun obtainCoffeesByType(serverRequest: ServerRequest): Mono<ServerResponse>
    fun obtainCoffeesByBarista(serverRequest: ServerRequest): Mono<ServerResponse>
    fun obtainCoffeesByClient(serverRequest: ServerRequest): Mono<ServerResponse>
    fun deleteById(serverRequest: ServerRequest): Mono<ServerResponse>
}

interface CoroutineMongo: CoroutineCrudRepository<CoffeeDomain, String> {

}
