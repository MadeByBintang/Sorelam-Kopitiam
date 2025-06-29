package com.example.sorelamkopitiam.domain.model

data class OrderItem(
    val id: Int = 0,
    val date: String,
    val items: String,
    val quantity: Int,
    val totalPrice: Int,
    val status: String,
    val shot: String,
    val size: String,
    val ice: String
)
