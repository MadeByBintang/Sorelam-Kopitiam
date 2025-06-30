package com.example.sorelamkopitiam.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val items: String,
    val quantity: Int,
    val totalPrice: Int,
    val status: String,
    val shot: String,
    val size: String,
    val ice: String
)
