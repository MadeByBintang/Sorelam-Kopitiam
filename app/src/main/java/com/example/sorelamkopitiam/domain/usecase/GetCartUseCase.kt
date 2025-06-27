package com.example.sorelamkopitiam.domain.usecase

import com.example.sorelamkopitiam.domain.repository.CartRepository

class GetCartUseCase(private val repository: CartRepository) {
    operator fun invoke() = repository.getAllCartItems()
}
