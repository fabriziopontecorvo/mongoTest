package com.example.mongotest.application.useCase

import arrow.core.computations.either
import com.example.mongotest.adapter.model.RequestCoffee
import com.example.mongotest.adapter.repository.AsyncCoffeeRepository
import com.example.mongotest.domain.CoffeeDomain
import com.example.mongotest.application.port.`in`.AsyncCoffeeInputPort
import kotlinx.coroutines.flow.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.*

@Service
class AsyncStarbucks(
    val repository: AsyncCoffeeRepository
) : AsyncCoffeeInputPort {


    override suspend fun asyncCreateCoffee(serverRequest: ServerRequest): ServerResponse =
        ServerResponse
            .ok()
            .json()
            .bodyAndAwait(
                serverRequest
                    .bodyToFlow<RequestCoffee>()
                    .map { it.toDomain() }
                    .let {
                        either<Exception, Flow<CoffeeDomain>> {
                            repository.saveAll(it)
                        }
                            .fold({ flowOf(DomainError()) }, { it })
                    }
            )

    override suspend fun asyncObtainCoffees(serverRequest: ServerRequest): ServerResponse =
        ServerResponse
            .ok()
            .json()
            .bodyAndAwait(
                either<Exception, Flow<CoffeeDomain>> {
                    repository.findAll()
                }
                    .fold({ flowOf(DomainError()) }, { it })

            )

    override suspend fun asyncObtainCoffeesByType(serverRequest: ServerRequest): ServerResponse =

        serverRequest.pathVariables()["type"]?.let {
            ServerResponse
                .ok()
                .json()
                .bodyAndAwait(
                    either<Exception, Flow<CoffeeDomain>> {
                        repository.findByType(it)
                    }
                        .fold({ flowOf(DomainError()) }, { it })

                )
        } ?: ServerResponse
            .badRequest()
            .bodyAndAwait(flowOf(BadRequest()))


    override suspend fun asyncObtainCoffeesByBarista(serverRequest: ServerRequest): ServerResponse =
        serverRequest.pathVariables()["barista"]?.let {
            ServerResponse
                .ok()
                .json()
                .bodyAndAwait(
                    either<Exception, Flow<CoffeeDomain>> {
                        repository.findByBarista(it)
                    }
                        .fold({ flowOf(DomainError()) }, { it })

                )
        } ?: ServerResponse
            .badRequest()
            .bodyAndAwait(flowOf(BadRequest()))


    override suspend fun asyncObtainCoffeesByClient(serverRequest: ServerRequest): ServerResponse =
        serverRequest.pathVariables()["client"]?.let {
            ServerResponse
                .ok()
                .json()
                .bodyAndAwait(
                    either<Exception, Flow<CoffeeDomain>> {
                        repository.findByClient(it)
                    }
                        .fold({ flowOf(DomainError()) }, { it })

                )
        } ?: ServerResponse
            .badRequest()
            .bodyAndAwait(flowOf(BadRequest()))

    override suspend fun asyncDeleteById(serverRequest: ServerRequest): ServerResponse =
        serverRequest.pathVariables()["id"]?.let {
            ServerResponse
                .ok()
                .json()
                .bodyAndAwait(
                    either<Exception, Flow<CoffeeDomain>> {
                        repository.findAllById(flowOf(it))
                            .also { repository.deleteAll(it) }
                    }
                        .fold({ flowOf(DomainError()) }, { it })

                )
        } ?: ServerResponse
            .badRequest()
            .bodyAndAwait(flowOf(BadRequest()))


}

sealed interface Error

data class BadRequest(
    val status: HttpStatus = HttpStatus.BAD_REQUEST,
    val message: String = "Nop!",
    val code: Int = 400
) : Error

data class DomainError(
    val status: HttpStatus = HttpStatus.UNPROCESSABLE_ENTITY,
    val message: String = "Nop!",
    val code: Int = 422
) : Error
