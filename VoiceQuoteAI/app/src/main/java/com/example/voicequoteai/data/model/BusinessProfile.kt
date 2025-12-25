package com.example.voicequoteai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "business_profile")
data class BusinessProfile(
    @PrimaryKey
    val id: Int = 1,   // Single profile logic
    val businessName: String,
    val ownerName: String,
    val phone: String,
    val email: String,
    val gstNumber: String
)
