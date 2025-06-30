package com.example.sorelamkopitiam.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sorelamkopitiam.data.local.dao.CartDao
import com.example.sorelamkopitiam.data.local.dao.OrderDao
import com.example.sorelamkopitiam.data.local.dao.RedeemDao
import com.example.sorelamkopitiam.data.local.dao.RewardDao
import com.example.sorelamkopitiam.data.local.entity.CartEntity
import com.example.sorelamkopitiam.data.local.entity.OrderEntity
import com.example.sorelamkopitiam.data.local.entity.RedeemEntity
import com.example.sorelamkopitiam.data.local.entity.RewardEntity

@Database(
    entities = [CartEntity::class, OrderEntity::class, RewardEntity::class, RedeemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao
    abstract fun rewardDao(): RewardDao
    abstract fun redeemDao(): RedeemDao

}
