package com.example.sorelamkopitiam.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.example.sorelamkopitiam.util.dummyCoffeeMenu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState(menuItems = dummyCoffeeMenu))
    val state: StateFlow<HomeUiState> = _state
}
