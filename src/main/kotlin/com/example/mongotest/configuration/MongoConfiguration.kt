package com.example.mongotest.configuration

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories


@EnableReactiveMongoRepositories
class MongoReactiveApplication : AbstractReactiveMongoConfiguration() {

    @Bean
    fun mongoClient(): MongoClient = MongoClients.create()

    override fun getDatabaseName() = "mydbname"

}

@Configuration
class ReactiveMongoConfig(
    val mongoClient: MongoClient
) {

    @Bean
    fun reactiveMongoTemplate(): ReactiveMongoTemplate =
         ReactiveMongoTemplate(mongoClient, "mydbname")

}
