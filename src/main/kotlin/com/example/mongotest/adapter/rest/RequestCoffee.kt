package com.example.mongotest.adapter.rest

import com.example.mongotest.application.model.CoffeeDomain
import java.util.*

data class RequestCoffee(
    val type: TypeCoffee = TypeCoffee.EXPRESSO,
    val barista: String,
    val client: String
) {
    fun toDomain() =
        CoffeeDomain(
            id = UUID.randomUUID().toString(),
            type = type,
            barista = barista,
            client = client,
        )
}

enum class TypeCoffee(val code: String) {
    EXPRESSO("EXPRESSO"),
    LATE("LATE"),
    MOCKA("MOCKA");

    companion object {
        private val mapByCode = values().associateBy(TypeCoffee::code)

        fun from(code: String) = mapByCode[code.uppercase()] ?: EXPRESSO
    }

}
