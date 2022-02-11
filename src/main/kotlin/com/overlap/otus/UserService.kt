package com.overlap.otus

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

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

    fun getUser(login: String): User? =
        users.findByLogin(login)

    fun getUsers(firstName: String, lastName: String): List<User> =
        users.findAll(firstName, lastName)
}
