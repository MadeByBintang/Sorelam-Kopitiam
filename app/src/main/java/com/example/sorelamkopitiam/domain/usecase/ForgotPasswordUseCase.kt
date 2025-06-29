package com.example.sorelamkopitiam.domain.usecase

import com.example.sorelamkopitiam.domain.repository.AuthRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String) = repository.sendPasswordResetEmail(email)
}