package com.example.quotationapp.model

import java.io.Serializable

data class ComponentModel(
    val id: Int,
    val name: String,
    val price: String,
    val imageUri: String,
    var quantity: Int = 1
) : Serializable
