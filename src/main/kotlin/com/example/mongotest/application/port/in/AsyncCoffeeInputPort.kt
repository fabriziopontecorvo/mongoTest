package com.example.mongotest.application.port.`in`

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

interface AsyncCoffeeInputPort {
    suspend fun asyncCreateCoffee(serverRequest: ServerRequest): ServerResponse
    suspend fun asyncObtainCoffees(serverRequest: ServerRequest): ServerResponse
    suspend fun asyncObtainCoffeesByType(serverRequest: ServerRequest): ServerResponse
    suspend fun asyncObtainCoffeesByBarista(serverRequest: ServerRequest): ServerResponse
    suspend fun asyncObtainCoffeesByClient(serverRequest: ServerRequest): ServerResponse
    suspend fun asyncDeleteById(serverRequest: ServerRequest): ServerResponse
}

