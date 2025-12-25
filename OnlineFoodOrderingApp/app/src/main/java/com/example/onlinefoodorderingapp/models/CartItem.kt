package com.example.onlinefoodorderingapp.models

data class CartItem(
    val id: Int = 0,
    val foodId: Int,
    val foodName: String,
    val quantity: Int,
    val price: Double
)
