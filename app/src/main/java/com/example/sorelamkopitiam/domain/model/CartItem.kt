package com.example.sorelamkopitiam.domain.model

data class CartItem(
    val id: Int,
    val name: String,
    val priceLabel: String,
    val imageRes: Int,
    val price: Int,
    val quantity: Int,
    val shot: String,
    val select: String,
    val size: String,
    val ice: String,
    val productId: Int
)
