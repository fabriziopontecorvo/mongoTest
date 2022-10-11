package com.example.mongotest.adapter.rest

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findAndRemove
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.*
import reactor.core.CorePublisher
import reactor.core.publisher.Mono

@Service
class CoffeeAdapter(
    val template: ReactiveMongoTemplate
) {

    fun createCoffee(serverRequest: ServerRequest): Mono<ServerResponse> =
        response {
            serverRequest.bodyToMono<RequestCoffee>()
                .flatMap {
                    template.save(it)
                }
        }


    fun obtainCoffees(): Mono<ServerResponse> =
        response { template.findAll() }


    fun obtainCoffeesByType(serverRequest: ServerRequest): Mono<ServerResponse> =
        response {
            template.find(
                Query.query(
                    Criteria()
                        .andOperator(Criteria.where("type").isEqualTo(serverRequest.pathVariables()["type"]))
                )
            )
        }


    fun obtainCoffeesByBarista(serverRequest: ServerRequest): Mono<ServerResponse> =
        response {
            template.find(
                Query.query(
                    Criteria()
                        .andOperator(Criteria.where("barista").isEqualTo(serverRequest.pathVariables()["barista"]))
                )
            )
        }


    suspend fun obtainCoffeesByClient(serverRequest: ServerRequest): ServerResponse =
        responseAsync {
            template.find(
                Query.query(
                    Criteria()
                        .andOperator(
                            Criteria.where(serverRequest.pathVariables().keys.first())
                                .isEqualTo(serverRequest.pathVariables()["client"])
                        )
                )
            )
        }

    fun deleteById(serverRequest: ServerRequest): Mono<ServerResponse> =
        response {
            template.findAndRemove(
                Query.query(
                    Criteria()
                        .andOperator(Criteria.where("id").isEqualTo(serverRequest.pathVariables()["id"]))
                )
            )
        }

    private suspend fun responseAsync(f: () -> CorePublisher<Any>): ServerResponse =
        ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(
                f()
            )

    private fun response(f: () -> CorePublisher<Any>): Mono<ServerResponse> =
        ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                f()
            )
}


