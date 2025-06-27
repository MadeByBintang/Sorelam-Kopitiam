package com.example.sorelamkopitiam.domain.repository

import com.example.sorelamkopitiam.domain.model.RewardItem
import kotlinx.coroutines.flow.Flow

interface RewardsRepository {
    fun getAllRewards(): Flow<List<RewardItem>>
    suspend fun insertReward(title: String, caption: String, date: String, points: Int)
    suspend fun deleteReward(id: Int)
    suspend fun clearRewards()
}
