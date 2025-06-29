package com.example.sorelamkopitiam.presentation.screen.auth

// Kita bisa gunakan satu data class untuk state Sign In dan Sign Up
data class AuthState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)