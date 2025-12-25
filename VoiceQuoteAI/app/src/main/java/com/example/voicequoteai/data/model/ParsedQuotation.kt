package com.voicequoteai.data.model

data class ParsedQuotation(
    val clientName: String,
    val service: String,
    val amount: Double,
    val advancePercent: Int,
    val deliveryTimeline: String,
    val revisionCount: Int
)
