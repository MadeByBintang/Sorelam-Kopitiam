package com.example.sorelamkopitiam.data.local.dao

import androidx.room.*
import com.example.sorelamkopitiam.data.local.entity.RedeemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RedeemDao {
    @Query("SELECT * FROM redeem")
    fun getAllRedeemItems(): Flow<List<RedeemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(redeem: RedeemEntity)

    @Delete
    suspend fun delete(redeem: RedeemEntity)

    @Query("DELETE FROM redeem")
    suspend fun clearAll()
}