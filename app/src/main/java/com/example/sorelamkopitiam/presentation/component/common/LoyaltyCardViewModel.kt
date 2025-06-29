package com.example.sorelamkopitiam.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorelamkopitiam.domain.repository.RewardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoyaltyCardViewModel @Inject constructor(
    private val rewardsRepository: RewardsRepository
) : ViewModel() {

    val rewards = rewardsRepository.getAllRewards()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val loyaltyCount = rewards.map { rewardsList ->
        rewardsList.count { !it.isRedeem }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val points = rewards.map { rewardsList ->
        rewardsList.sumOf { if (it.isRedeem) -it.points else it.points }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun clearAll() {
        viewModelScope.launch {
            rewardsRepository.clearRewards()
        }
    }

    fun clearLoyaltyProgress() {
        viewModelScope.launch {
            val currentRewards = rewards.value
            val activeLoyalty = currentRewards.filter { !it.isRedeem }
            activeLoyalty.take(8).forEach {
                rewardsRepository.deleteReward(it.id)
            }
        }
    }
}