package com.example.sorelamkopitiam.presentation.screen.auth

data class AuthState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)