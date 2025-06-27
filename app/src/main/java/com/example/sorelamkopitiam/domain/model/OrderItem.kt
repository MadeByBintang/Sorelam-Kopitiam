package com.example.sorelamkopitiam.domain.model

data class OrderItem(
    val id: Int = 0,
    val date: String,
    val items: String,      // Bisa di-join dari list item ke string
    val quantity: Int, // 🔥 Tambahkan ini!
    val totalPrice: Int,
    val status: String,      // "ongoing" atau "history"
    val shot: String,    // ⬅️ Tambah ini
    val size: String,    // ⬅️ Tambah ini
    val ice: String      // ⬅️ Tambah ini
)
