package com.example.sorelamkopitiam.domain.usecase

import com.example.sorelamkopitiam.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, email: String, password: String) =
        repository.signUp(username, email, password)
}