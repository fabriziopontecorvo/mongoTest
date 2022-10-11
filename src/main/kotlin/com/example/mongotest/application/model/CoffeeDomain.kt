package com.example.mongotest.application.model

import com.example.mongotest.rest.model.TypeCoffee
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class CoffeeDomain(
    @Id
    val id: UUID,
    val type: TypeCoffee,
    val barista: String,
    val client: String,
)

