package com.overlap.otus

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.stereotype.Service

@Service
class UserService(
    private val users: UsersRepository,
    private val authenticationManager: ReactiveAuthenticationManager
) {

    fun register(userRequest: UserRequest) {
        if (users.isExists(userRequest.login)) {
            authenticationManager.authenticate(AuthRequest(userRequest)).subscribe()
        } else {
            users.save(userRequest)
            authenticationManager.authenticate(AuthRequest(userRequest)).subscribe()
        }
    }

    fun getUser(login: String): User? {
        return users.findByLogin(login)
    }
}

