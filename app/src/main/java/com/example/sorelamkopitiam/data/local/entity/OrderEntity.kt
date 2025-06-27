package com.example.sorelamkopitiam.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val items: String,       // List item disimpan sebagai String (misal join dengan koma)
    val quantity: Int, // ✅ Ini wajib ada
    val totalPrice: Int,
    val status: String,       // "ongoing" atau "history"
    val shot: String,    // ⬅️ Tambah ini
    val size: String,    // ⬅️ Tambah ini
    val ice: String      // ⬅️ Tambah ini
)
