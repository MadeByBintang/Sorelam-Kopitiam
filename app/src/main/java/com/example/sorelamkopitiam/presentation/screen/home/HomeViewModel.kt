package com.example.sorelamkopitiam.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorelamkopitiam.domain.model.CoffeeItem
import com.example.sorelamkopitiam.domain.model.User
import com.example.sorelamkopitiam.domain.repository.AuthRepository
import com.example.sorelamkopitiam.domain.repository.RewardsRepository
import com.example.sorelamkopitiam.util.dummyCoffeeMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val rewardsRepository: RewardsRepository,
    private val authRepository: AuthRepository // Inject AuthRepository
) : ViewModel() {

    // State untuk menyimpan data user
    private val _userState = MutableStateFlow<User?>(null)
    val userState = _userState.asStateFlow()

    init {
        // Ambil data user saat ViewModel dibuat
        viewModelScope.launch {
            authRepository.getUserDetails()
                .onSuccess { _userState.value = it }
        }
    }

    // ... (kode lainnya untuk menu, points, dll. tetap sama)
    data class HomeUiState(
        val isLoading: Boolean = false,
        val menuItems: List<CoffeeItem> = emptyList(),
        val errorMessage: String? = null
    )
    private val _state = MutableStateFlow(HomeUiState(menuItems = dummyCoffeeMenu))
    val state: StateFlow<HomeUiState> = _state

    val rewards = rewardsRepository.getAllRewards()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val points = rewards.map { rewardsList ->
        rewardsList.sumOf { if (it.isRedeem) -it.points else it.points }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
}