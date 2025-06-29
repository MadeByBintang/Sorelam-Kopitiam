package com.example.sorelamkopitiam.presentation.navigation

import androidx.lifecycle.ViewModel
import com.example.sorelamkopitiam.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppNavigationViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    // Fungsi ini akan kita gunakan untuk mengecek status login user
    fun isLoggedIn(): Boolean {
        return repo.currentUser != null
    }
}