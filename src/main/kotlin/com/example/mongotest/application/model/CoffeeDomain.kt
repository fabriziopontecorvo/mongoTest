package com.example.mongotest.application.model

import com.example.mongotest.adapter.rest.TypeCoffee
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class CoffeeDomain(
    @Id
    val id: String,
    val type: TypeCoffee,
    val barista: String,
    val client: String,
)

