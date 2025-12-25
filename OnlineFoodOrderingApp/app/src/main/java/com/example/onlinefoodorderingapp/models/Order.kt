package com.example.onlinefoodorderingapp.models

data class Order(
    val id: Int = 0,
    val orderId: String,          // unique QR code value
    val foodSummary: String,      // food name, quantity, price list
    val totalAmount: Double,
    val isVerified: Int = 0       // 0 = not verified, 1 = verified
)
