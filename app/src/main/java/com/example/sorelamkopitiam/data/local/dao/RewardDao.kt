package com.example.sorelamkopitiam.data.local.dao

import androidx.room.*
import com.example.sorelamkopitiam.data.local.entity.RewardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RewardDao {
    @Query("SELECT * FROM rewards ORDER BY id DESC")
    fun getAllRewards(): Flow<List<RewardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReward(reward: RewardEntity)

    @Delete
    suspend fun deleteReward(reward: RewardEntity)

    @Query("DELETE FROM rewards")
    suspend fun clearRewards()
}
