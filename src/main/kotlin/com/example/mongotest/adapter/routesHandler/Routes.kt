package com.example.mongotest.adapter.routesHandler

import com.example.mongotest.adapter.rest.CoffeeAdapter
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.coRouter

@Component
class DslCoroutineRouter {

    @Bean
    fun route(handler: CoffeeAdapter) = router {
        (accept(APPLICATION_JSON) and "/coffees").nest {
            POST { handler.createCoffee(it) }
            GET { handler.obtainCoffees() }
            GET("/type/{type}").invoke { handler.obtainCoffeesByType(it) }
            GET("/barista/{barista}").invoke { handler.obtainCoffeesByBarista(it) }
            DELETE("/{id}").invoke { handler.deleteById(it) }
        }
    }

    @Bean
    fun coRoute(handler: CoffeeAdapter) = coRouter {
        (accept(APPLICATION_JSON) and "/coffees").nest {
            GET("/client/{client}").invoke { handler.obtainCoffeesByClient(it) }
        }
    }
}

