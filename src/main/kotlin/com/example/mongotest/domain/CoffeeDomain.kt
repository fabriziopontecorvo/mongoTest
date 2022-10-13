package com.example.mongotest.domain

import com.example.mongotest.adapter.model.TypeCoffee
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class CoffeeDomain(
    @Id
    val id: String = "",
    val type: String = TypeCoffee.EXPRESSO.toString(),
    val barista: String = "",
    val client: String = "",
)

