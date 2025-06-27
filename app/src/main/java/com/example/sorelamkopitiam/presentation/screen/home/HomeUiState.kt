package com.example.sorelamkopitiam.presentation.screen.home

import com.example.sorelamkopitiam.domain.model.CoffeeItem

data class HomeUiState(
    val isLoading: Boolean = false,
    val menuItems: List<CoffeeItem> = emptyList(),
    val errorMessage: String? = null
)
