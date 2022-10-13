package com.example.mongotest.adapter.model

import com.example.mongotest.domain.CoffeeDomain
import java.util.*

data class RequestCoffee(
    val type: TypeCoffee = TypeCoffee.EXPRESSO,
    val barista: String,
    val client: String
) {
    fun toDomain() =
        CoffeeDomain(
            id = UUID.randomUUID().toString(),
            type = type.toString(),
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
