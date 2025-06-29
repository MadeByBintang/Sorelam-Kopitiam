package com.example.sorelamkopitiam.data.repository

import com.example.sorelamkopitiam.data.remote.AuthRemoteDataSource
import com.example.sorelamkopitiam.domain.model.User
import com.example.sorelamkopitiam.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    // --- TAMBAHKAN KATA 'override' DI SINI ---
    override val currentUser: FirebaseUser?
        get() = remoteDataSource.currentUser // Ambil dari remote data source

    override suspend fun signUp(username: String, email: String, password: String): Result<Unit> {
        return try {
            val firebaseUser = remoteDataSource.signUp(email, password)
            if (firebaseUser != null) {
                // Panggil saveUserDetails dengan parameter yang sudah diperbarui
                remoteDataSource.saveUserDetails(firebaseUser.uid, username, email)
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to create user."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signIn(email: String, password: String): Result<Unit> {
        return try {
            remoteDataSource.signIn(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserDetails(): Result<User> {
        return try {
            val user = remoteDataSource.getUserDetails()
            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(Exception("User not found or not logged in."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signOut() {
        remoteDataSource.signOut()
    }
}