package com.example.sorelamkopitiam.presentation.screen.rewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorelamkopitiam.domain.model.RewardItem
import com.example.sorelamkopitiam.domain.repository.RewardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RewardsViewModel @Inject constructor(
    private val repository: RewardsRepository
) : ViewModel() {

    val rewards: StateFlow<List<RewardItem>> = repository.getAllRewards()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun deleteReward(id: Int) {
        viewModelScope.launch {
            repository.deleteReward(id)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.clearRewards()
        }
    }
}