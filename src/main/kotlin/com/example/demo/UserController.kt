package com.example.demo

import java.security.Principal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class UserController(private val userService: UserService) {

    @ResponseBody
    @GetMapping("/user")
    fun getUser(principal: Principal): Principal {
        return principal
    }

    @ResponseBody
    @PostMapping("/signup")
    fun registerUser(userRequest: UserRequest): User? {
        return userService.signup(userRequest)
    }

    @GetMapping("/signup")
    fun signupPage(): String {
        return "signup"
    }
}