package com.example.sorelamkopitiam.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val productId: Int,
    val priceLabel: String,
    val price: Int,
    val quantity: Int,
    val imageRes: Int,
    val shot: String,
    val select: String,
    val size: String,
    val ice: String
)
