package com.example.sorelamkopitiam.domain.usecase

import com.example.sorelamkopitiam.domain.repository.CoffeeRepository

class GetCoffeeMenuUseCase(private val repository: CoffeeRepository) {
    operator fun invoke() = repository.getCoffeeMenu()
}
