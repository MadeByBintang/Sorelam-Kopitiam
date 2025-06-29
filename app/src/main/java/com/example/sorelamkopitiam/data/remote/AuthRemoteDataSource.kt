package com.example.sorelamkopitiam.data.remote

import com.example.sorelamkopitiam.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    // --- TAMBAHKAN PROPERTI INI ---
    val currentUser: com.google.firebase.auth.FirebaseUser?
        get() = firebaseAuth.currentUser

    // Mengembalikan Firebase User agar bisa kita ambil UID-nya
    suspend fun signUp(email: String, password: String): com.google.firebase.auth.FirebaseUser? {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
    }

    suspend fun signIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    // Fungsi untuk menyimpan data user ke Firestore
    suspend fun saveUserDetails(uid: String, username: String, email: String) {
        val user = User(
            uid = uid,
            username = username,
            email = email
        )
        firestore.collection("users").document(uid).set(user).await()
    }

    suspend fun getUserDetails(): User? {
        val uid = firebaseAuth.currentUser?.uid ?: return null
        return firestore.collection("users").document(uid).get().await().toObject(User::class.java)
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    suspend fun sendPasswordResetEmail(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).await()
    }
}