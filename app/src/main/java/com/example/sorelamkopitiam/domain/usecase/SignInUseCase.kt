package com.example.sorelamkopitiam.domain.usecase

import com.example.sorelamkopitiam.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = repository.signIn(email, password)
}