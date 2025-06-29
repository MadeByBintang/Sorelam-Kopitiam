package com.example.sorelamkopitiam.domain.repository

import com.example.sorelamkopitiam.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?

    // Menambahkan parameter sesuai UI Anda
    suspend fun signUp(username: String, email: String, password: String): Result<Unit>
    suspend fun signIn(email: String, password: String): Result<Unit>
    suspend fun getUserDetails(): Result<User>
    suspend fun signOut()
}