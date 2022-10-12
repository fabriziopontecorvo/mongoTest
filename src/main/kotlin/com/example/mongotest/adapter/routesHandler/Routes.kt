package com.example.mongotest.adapter.routesHandler

import com.example.mongotest.application.useCase.Starbucks
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class Routes {

    @Bean
    fun route(handler: Starbucks) = router {
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

