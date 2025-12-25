package com.voicequoteai.data.model

data class QuotationSummary(
    val clientName: String,
    val totalAmount: Double,
    val gstAmount: Double,
    val finalAmount: Double
)
