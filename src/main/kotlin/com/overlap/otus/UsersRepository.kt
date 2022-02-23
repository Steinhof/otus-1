package com.overlap.otus

import com.fasterxml.jackson.databind.ObjectMapper
import org.jooq.DSLContext
import org.jooq.JSON
import org.jooq.generated.Tables.USER
import org.springframework.stereotype.Repository

@Repository
class UsersRepository(private val dsl: DSLContext, private val objectMapper: ObjectMapper) {

    fun isExists(login: String): Boolean {
        val user = dsl
            .select()
            .from(USER)
            .where(USER.LOGIN.eq(login))
            .fetchOneInto(User::class.java)

        return user != null
    }

    fun findByLogin(login: String): User? {
        return dsl
            .select()
            .from(USER)
            .where(USER.LOGIN.eq(login))
            .fetchOneInto(User::class.java)
    }

    fun findAll(firstName: String, lastName: String): List<User> {
        return dsl
            .select()
            .from(USER)
            .where(USER.FIRST_NAME.like("$firstName%").and(USER.LAST_NAME.like("$lastName%")))
            .fetchInto(User::class.java)
    }

    fun save(userRequest: UserRequest): User? {
        val newUser = dsl.newRecord(USER, userRequest)
        newUser.interests = JSON.json(objectMapper.writeValueAsString(userRequest.interests))

        return dsl
            .insertInto(USER)
            .set(newUser)
            .returning()
            .fetchOneInto(User::class.java)
    }
}
