package com.overlap.otus

import org.springframework.stereotype.Service

@Service
class UserService(private val users: UsersRepository) {

    fun signup(userRequest: UserRequest): User? {
        if (users.isExists(userRequest.login)) {
            return null
        }

        return users.save(userRequest)
    }
}