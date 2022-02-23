package com.overlap.otus

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserResource(private val userService: UserService) {
    private var counter = 0;

    @GetMapping("/users")
    fun getUsers(firstName: String, lastName: String): List<User> {
        println(++counter)
        return userService.getUsers(firstName, lastName)
    }
}