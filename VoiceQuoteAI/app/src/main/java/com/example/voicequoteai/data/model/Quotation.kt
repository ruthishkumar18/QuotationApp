package com.example.voicequoteai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotations")
data class Quotation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val clientName: String,
    val service: String,
    val amount: String,
    val advance: String,
    val delivery: String,
    val date: String
)
