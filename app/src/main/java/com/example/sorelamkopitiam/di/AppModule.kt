package com.example.sorelamkopitiam.di

import android.content.Context
import androidx.room.Room
import com.example.sorelamkopitiam.data.local.dao.CartDao
import com.example.sorelamkopitiam.data.local.dao.OrderDao
import com.example.sorelamkopitiam.data.local.dao.RedeemDao
import com.example.sorelamkopitiam.data.local.dao.RewardDao
import com.example.sorelamkopitiam.data.local.database.AppDatabase
import com.example.sorelamkopitiam.data.repository.AuthRepositoryImpl
import com.example.sorelamkopitiam.data.repository.CartRepositoryImpl
import com.example.sorelamkopitiam.data.repository.OrderRepositoryImpl
import com.example.sorelamkopitiam.data.repository.RedeemRepositoryImpl
import com.example.sorelamkopitiam.data.repository.RewardsRepositoryImpl
import com.example.sorelamkopitiam.domain.repository.AuthRepository
import com.example.sorelamkopitiam.domain.repository.CartRepository
import com.example.sorelamkopitiam.domain.repository.OrderRepository
import com.example.sorelamkopitiam.domain.repository.RedeemRepository
import com.example.sorelamkopitiam.domain.repository.RewardsRepository
import com.example.sorelamkopitiam.data.remote.AuthRemoteDataSource
import com.example.sorelamkopitiam.domain.usecase.ForgotPasswordUseCase
import com.example.sorelamkopitiam.domain.usecase.SignInUseCase
import com.example.sorelamkopitiam.domain.usecase.SignUpUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
            .fallbackToDestructiveMigration(true)
            .build()
    }

    // ✅ DAO
    @Provides fun provideCartDao(db: AppDatabase): CartDao = db.cartDao()
    @Provides fun provideOrderDao(db: AppDatabase): OrderDao = db.orderDao()
    @Provides fun provideRewardDao(db: AppDatabase): RewardDao = db.rewardDao()
    @Provides fun provideRedeemDao(db: AppDatabase): RedeemDao = db.redeemDao()

    // ✅ Repository
    @Provides
    fun provideCartRepository(dao: CartDao): CartRepository =
        CartRepositoryImpl(dao)

    @Provides
    fun provideOrderRepository(dao: OrderDao): OrderRepository =
        OrderRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideRewardsRepository(dao: RewardDao): RewardsRepository =
        RewardsRepositoryImpl(dao)

    @Provides
    fun provideRedeemRepository(dao: RedeemDao): RedeemRepository =
        RedeemRepositoryImpl(dao)

    // ✅ Firebase
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(auth: FirebaseAuth, firestore: FirebaseFirestore): AuthRemoteDataSource {
        return AuthRemoteDataSource(auth, firestore)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(remoteDataSource: AuthRemoteDataSource): AuthRepository {
        // Karena AuthRepositoryImpl hanya punya 1 parameter, kita bisa langsung
        return AuthRepositoryImpl(remoteDataSource)
    }

    // Use Cases tidak perlu @Singleton karena mereka stateless
    @Provides
    fun provideSignUpUseCase(repository: AuthRepository): SignUpUseCase {
        return SignUpUseCase(repository)
    }

    @Provides
    fun provideSignInUseCase(repository: AuthRepository): SignInUseCase {
        return SignInUseCase(repository)
    }

    @Provides
    fun provideForgotPasswordUseCase(repository: AuthRepository): ForgotPasswordUseCase {
        return ForgotPasswordUseCase(repository)
    }
}