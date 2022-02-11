package com.overlap.otus

import com.example.otus.database.Tables.USER
import com.example.otus.database.tables.records.UserRecord
import io.github.serpro69.kfaker.faker
import org.jooq.DSLContext
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.Executors
import java.util.concurrent.ForkJoinPool
import java.util.stream.IntStream

@Component
class FakeData(private val dsl: DSLContext) {

    @EventListener(ApplicationStartedEvent::class)
    fun generate() {
        val faker = faker {}
        val pool = Executors.newWorkStealingPool(15)

        for (i in 1..1_000_000) {
            pool.execute {
                val record = dsl.newRecord(USER).apply {
                    login = "${faker.hacker.noun()}${faker.random.nextInt(1, 9999)}"
                    firstName = faker.name.firstName()
                    lastName = faker.name.lastName()
                    age = faker.random.nextInt(18, 100)
                    interests = null
                    city = faker.nation.capitalCity()
                    password = "${faker.name.neutralFirstName()}${faker.random.nextLong()}"
                    gender = faker.gender.binaryTypes()
                }
                dsl.insertInto(USER).set(record).execute()
            }
        }

        print("end")
    }
}