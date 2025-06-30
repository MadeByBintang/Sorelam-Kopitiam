package com.example.sorelamkopitiam.domain.model

data class RewardItem(
    val id: Int,
    val title: String,
    val caption: String,
    val date: String,
    val points: Int,
    val isRedeem: Boolean,
    val isStamp: Boolean = false
)
