package com.example.sorelamkopitiam.di

import android.content.Context
import androidx.room.Room
import com.example.sorelamkopitiam.data.local.dao.CartDao
import com.example.sorelamkopitiam.data.local.dao.OrderDao
import com.example.sorelamkopitiam.data.local.dao.RedeemDao
import com.example.sorelamkopitiam.data.local.dao.RewardDao
import com.example.sorelamkopitiam.data.local.database.AppDatabase
import com.example.sorelamkopitiam.data.repository.CartRepositoryImpl
import com.example.sorelamkopitiam.data.repository.OrderRepositoryImpl
import com.example.sorelamkopitiam.data.repository.RedeemRepositoryImpl
import com.example.sorelamkopitiam.data.repository.RewardsRepositoryImpl
import com.example.sorelamkopitiam.domain.repository.CartRepository
import com.example.sorelamkopitiam.domain.repository.OrderRepository
import com.example.sorelamkopitiam.domain.repository.RedeemRepository
import com.example.sorelamkopitiam.domain.repository.RewardsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "sorelam_db"
        )
            .fallbackToDestructiveMigration(true) // ✅ Akan hapus dan rebuild DB otomatis saat versi naik
            .build()
    }

    @Provides
    fun provideCartDao(db: AppDatabase): CartDao = db.cartDao()

    @Provides
    fun provideOrderDao(db: AppDatabase): OrderDao = db.orderDao()

    @Provides
    fun provideCartRepository(dao: CartDao): CartRepository =
        CartRepositoryImpl(dao)

    @Provides
    fun provideOrderRepository(dao: OrderDao): OrderRepository =
        OrderRepositoryImpl(dao)

    @Provides
    fun provideRewardDao(db: AppDatabase): RewardDao = db.rewardDao()

    @Provides
    @Singleton
    fun provideRewardsRepository(dao: RewardDao): RewardsRepository {
        return RewardsRepositoryImpl(dao)
    }

    @Provides
    fun provideRedeemDao(db: AppDatabase): RedeemDao = db.redeemDao()

    @Provides
    fun provideRedeemRepository(dao: RedeemDao): RedeemRepository =
        RedeemRepositoryImpl(dao)
}