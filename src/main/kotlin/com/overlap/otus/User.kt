package com.overlap.otus

data class User(
    val id: Int,
    val login: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val interests: List<String>,
    val city: String
)
