package com.example.mongotest.adapter.routesHandler

import com.example.mongotest.application.port.`in`.AsyncCoffeeInputPort
import com.example.mongotest.application.port.`in`.CoffeeInputPort
import com.example.mongotest.application.useCase.Starbucks
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.router

@Configuration
class CoffeeRoutes {

    @Bean
    fun route(handler: CoffeeInputPort) = router {
        (accept(APPLICATION_JSON) and "/coffees").nest {
            POST("", handler::createCoffee)
            GET("", handler::obtainCoffees)
            GET("/types/{type}", handler::obtainCoffeesByType)
            GET("/baristas/{barista}", handler::obtainCoffeesByBarista)
            GET("/clients/{client}", handler::obtainCoffeesByClient)
            DELETE("/{id}", handler::deleteById)
        }
    }

}

