package com.example.demo

data class UserRequest(
    val login: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: Int,
    val interests: List<String>,
    val city: String
)
