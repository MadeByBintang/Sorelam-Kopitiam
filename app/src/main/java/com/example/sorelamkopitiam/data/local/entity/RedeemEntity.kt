package com.example.sorelamkopitiam.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "redeem")
data class RedeemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val imageRes: Int,
    val validUntil: String,
    val points: Int
)
