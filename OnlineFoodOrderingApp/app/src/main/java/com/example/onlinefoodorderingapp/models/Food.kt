package com.example.onlinefoodorderingapp.models

data class Food(
    val id: Int = 0,
    val name: String,
    val price: Double,
    val imagePath: String = ""   // optional. for future use
)
