package com.example.sorelamkopitiam.presentation.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorelamkopitiam.domain.usecase.ForgotPasswordUseCase
import com.example.sorelamkopitiam.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    // ViewModel ini hanya peduli pada SignInUseCase
    private val signInUseCase: SignInUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {
    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    private val _resetPasswordState = MutableStateFlow(AuthState())
    val resetPasswordState = _resetPasswordState.asStateFlow()

    fun onForgotPasswordClicked(email: String) {
        if (email.isBlank()) {
            _resetPasswordState.value = AuthState(error = "Email cannot be empty.")
            return
        }
        viewModelScope.launch {
            _resetPasswordState.value = AuthState(isLoading = true)
            forgotPasswordUseCase(email)
                .onSuccess {
                    _resetPasswordState.value = AuthState(isSuccess = true)
                }
                .onFailure {
                    _resetPasswordState.value = AuthState(error = it.message)
                }
        }
    }

    // Fungsi untuk mereset state setelah dialog ditutup
    fun resetPasswordState() {
        _resetPasswordState.value = AuthState()
    }

    fun onSignInClicked(email: String, pass: String) {
        if (email.isBlank() || pass.isBlank()) {
            _authState.value = AuthState(error = "Email and password cannot be empty.")
            return
        }
        viewModelScope.launch {
            _authState.value = AuthState(isLoading = true)
            // Memanggil UseCase, bukan repository
            signInUseCase(email, pass)
                .onSuccess {
                    _authState.value = AuthState(isSuccess = true)
                }
                .onFailure {
                    val errorMessage = when {
                        it.message?.contains("INVALID_LOGIN_CREDENTIALS") == true -> "Invalid email or password."
                        it.message?.contains("network") == true -> "Network error. Please check your connection."
                        else -> it.message ?: "An unknown error occurred."
                    }
                    _authState.value = AuthState(error = errorMessage)
                }
        }
    }
}