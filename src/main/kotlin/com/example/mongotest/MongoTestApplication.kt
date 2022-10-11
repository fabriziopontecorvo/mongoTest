package com.example.mongotest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MongoTestApplication

fun main(args: Array<String>) {
    runApplication<MongoTestApplication>(*args)
}
