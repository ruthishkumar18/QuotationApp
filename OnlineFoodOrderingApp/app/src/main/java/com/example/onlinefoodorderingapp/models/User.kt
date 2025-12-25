package com.example.foodordering.models

data class User(
    val id: Int = 0,
    val email: String,
    val role: String   // "STUDENT" or "STAFF"
)
