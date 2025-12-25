package com.example.voicequoteai.ai

data class AIResult(
    val clientName: String,
    val service: String,
    val amount: Double,
    val advancePercent: Int?,
    val deliveryTimeline: String?
)
