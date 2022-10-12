package com.example.mongotest.application.port.`in`

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

interface CoffeeInputPort {
    fun createCoffee(serverRequest: ServerRequest): Mono<ServerResponse>
    fun obtainCoffees(serverRequest: ServerRequest): Mono<ServerResponse>
    fun obtainCoffeesByType(serverRequest: ServerRequest): Mono<ServerResponse>
    fun obtainCoffeesByBarista(serverRequest: ServerRequest): Mono<ServerResponse>
    fun obtainCoffeesByClient(serverRequest: ServerRequest): Mono<ServerResponse>
    fun deleteById(serverRequest: ServerRequest): Mono<ServerResponse>
}
