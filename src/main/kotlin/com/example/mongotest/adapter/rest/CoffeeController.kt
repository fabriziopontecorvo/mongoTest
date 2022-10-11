package com.example.mongotest.adapter.rest

import com.example.mongotest.application.model.CoffeeDomain
import com.example.mongotest.application.port.out.CoffeeRepository
import com.example.mongotest.rest.model.RequestCoffee
import com.example.mongotest.rest.model.TypeCoffee
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class CoffeeController(
    val saveCoffee: CoffeeRepository
) {

    @PostMapping("/coffees")
    fun createCoffee(@RequestBody requestCoffee: RequestCoffee) =
        saveCoffee.insert(requestCoffee.toDomain())

    @GetMapping("/coffees")
    fun obtainCoffees(): List<CoffeeDomain> =
        saveCoffee.findAll()

    @GetMapping("/coffees/type/{type}")
    fun obtainCoffeesByType(@PathVariable type: String) =
        saveCoffee.findAllByType(TypeCoffee.from(type))

    @GetMapping("/coffees/barista/{barista}")
    fun obtainCoffeesByBarista(@PathVariable barista: String) =
        saveCoffee.findAllByBarista(barista)

    @GetMapping("/coffees/client/{client}")
    fun obtainCoffeesByClient(@PathVariable client: String) =
        saveCoffee.findAllByClient(client)

    @DeleteMapping("/coffees/{id}")
    fun deleteById(@PathVariable id: UUID) =
        saveCoffee.deleteById(id)

}
