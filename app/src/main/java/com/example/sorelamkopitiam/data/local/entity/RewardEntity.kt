package com.example.sorelamkopitiam.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rewards")
data class RewardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val caption: String,
    val date: String,
    val points: Int,
    val isRedeem: Boolean = false
)
