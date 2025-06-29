package com.example.sorelamkopitiam.presentation.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorelamkopitiam.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    // ViewModel ini hanya peduli pada SignUpUseCase
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    fun onSignUpClicked(username: String, email: String, pass: String) {
        // Hapus mobile.isBlank() dari validasi
        if (username.isBlank() || email.isBlank() || pass.isBlank()) {
            _authState.value = AuthState(error = "All fields must be filled.")
            return
        }
        if (pass.length < 6) {
            _authState.value = AuthState(error = "Password must be at least 6 characters.")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthState(isLoading = true)
            // Panggil use case dengan parameter yang sudah diperbarui
            signUpUseCase(username, email, pass)
                .onSuccess {
                    _authState.value = AuthState(isSuccess = true)
                }
                .onFailure {
                    _authState.value = AuthState(error = it.message)
                }
        }
    }
}