package com.example.sorelamkopitiam.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorelamkopitiam.domain.model.CoffeeItem
import com.example.sorelamkopitiam.domain.repository.RewardsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.sorelamkopitiam.util.dummyCoffeeMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val rewardsRepository: RewardsRepository
) : ViewModel() {

    // Menggabungkan HomeUiState ke dalam ViewModel
    data class HomeUiState(
        val isLoading: Boolean = false,
        val menuItems: List<CoffeeItem> = emptyList(),
        val errorMessage: String? = null
    )
    // MutableStateFlow untuk state HomeScreen
    private val _state = MutableStateFlow(HomeUiState(menuItems = dummyCoffeeMenu))
    val state: StateFlow<HomeUiState> = _state

    // Rewards untuk poin
    val rewards = rewardsRepository.getAllRewards()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Menghitung total poin berdasarkan rewards
    val points = rewards.map { rewardsList ->
        rewardsList.sumOf { if (it.isRedeem) -it.points else it.points }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
}
