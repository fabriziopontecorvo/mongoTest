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
class AsyncCoffeeRoutes {

    @Bean
    fun coRoute(handler: AsyncCoffeeInputPort) = coRouter {
        (accept(APPLICATION_JSON) and "/async-coffees").nest {
            POST("", handler::asyncCreateCoffee)
            GET("", handler::asyncObtainCoffees)
            GET("/types/{type}", handler::asyncObtainCoffeesByType)
            GET("/baristas/{barista}", handler::asyncObtainCoffeesByBarista)
            GET("/clients/{client}", handler::asyncObtainCoffeesByClient)
            DELETE("/{id}", handler::asyncDeleteById)
        }
    }

}

