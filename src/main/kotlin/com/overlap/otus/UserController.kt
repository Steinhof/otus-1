package com.overlap.otus

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.security.Principal

@Controller
class UserController(private val userService: UserService) {

    @GetMapping("/signup")
    fun signupPage(): String {
        return "signup"
    }

    @PostMapping("/signup")
    fun registerUser(userRequest: UserRequest): String {
        userService.register(userRequest)
        return "redirect:/profile"
    }

    @GetMapping("/profile")
    fun getProfile(model: Model, principal: Principal): String {
        model.addAttribute("user", userService.getUser(principal.name))

        return "profile"
    }
}
