package com.example.quotationapp.model

data class QuotationModel(
    val id: Int,
    val customerName: String,
    val customerMobile: String,
    val customerEmail: String,
    val pdfPath: String,
    val date: String,
    val status: String // PENDING / ACCEPTED / REJECTED
)

