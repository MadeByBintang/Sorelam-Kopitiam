package com.example.sorelamkopitiam.util

object RewardUtils {
    fun calculatePoints(totalPrice: Int): Int {
        return when {
            totalPrice >= 80000 -> 30
            totalPrice >= 50000 -> 20
            totalPrice >= 40000 -> 15
            else -> 10
        }
    }

    fun getCurrentDate(): String {
        val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        return dateFormat.format(java.util.Date())
    }
}
