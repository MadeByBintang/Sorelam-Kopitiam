package com.example.sorelamkopitiam.presentation.screen.profile

import com.example.sorelamkopitiam.domain.model.User

// State yang lebih lengkap untuk menangani semua kondisi UI
data class ProfileUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null
)