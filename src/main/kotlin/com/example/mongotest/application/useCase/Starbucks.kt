package com.example.mongotest.application.useCase

import com.example.mongotest.adapter.model.RequestCoffee
import com.example.mongotest.application.model.CoffeeDomain
import com.example.mongotest.application.port.`in`.CoffeeInputPort
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findAndRemove
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class Starbucks(
    val template: ReactiveMongoTemplate,
) : CoffeeInputPort {

    override fun createCoffee(serverRequest: ServerRequest): Mono<ServerResponse> =
        response {
            println("createCoffee")

            serverRequest.bodyToMono<RequestCoffee>()
                .flatMap {
                    template.save(it.toDomain())
                }
        }


    override fun obtainCoffees(serverRequest: ServerRequest): Mono<ServerResponse> =
        response {
            println("obtainCoffees")
            template.findAll<CoffeeDomain>()
        }

    override fun obtainCoffeesByType(serverRequest: ServerRequest): Mono<ServerResponse> =
        response {
            println("obtainCoffeesByType")
            template.find<CoffeeDomain>(
                Query.query(
                    Criteria()
                        .andOperator(
                            Criteria
                                .where("type")
                                .isEqualTo(serverRequest.pathVariables()["type"])
                        )
                )
            )
        }

    override fun obtainCoffeesByBarista(serverRequest: ServerRequest): Mono<ServerResponse> =
        response {
            println("obtainCoffeesByBarista")

            template.find<CoffeeDomain>(
                Query.query(
                    Criteria()
                        .andOperator(
                            Criteria
                                .where("barista")
                                .isEqualTo(serverRequest.pathVariables()["barista"])
                        )
                )
            )
        }

    override fun obtainCoffeesByClient(serverRequest: ServerRequest): Mono<ServerResponse> = response {
        println("obtainCoffeesByClient")

        template.find<CoffeeDomain>(
            Query.query(
                Criteria()
                    .andOperator(
                        Criteria
                            .where("client")
                            .isEqualTo(serverRequest.pathVariables()["client"])
                    )
            )
        )
    }

    override fun deleteById(serverRequest: ServerRequest): Mono<ServerResponse> =
        response {
            println("deleteById")

            template.findAndRemove<CoffeeDomain>(
                Query.query(
                    Criteria()
                        .andOperator(
                            Criteria
                                .where("id")
                                .isEqualTo(serverRequest.pathVariables()["id"])
                        )
                )
            )
        }

    private fun response(f: () -> Any): Mono<ServerResponse> =
        ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                f(), Flux::class.java
            )
}


