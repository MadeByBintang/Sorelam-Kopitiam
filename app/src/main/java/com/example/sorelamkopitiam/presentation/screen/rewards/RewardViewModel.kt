package com.example.sorelamkopitiam.presentation.screen.rewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorelamkopitiam.domain.model.RewardItem
import com.example.sorelamkopitiam.domain.repository.CartRepository
import com.example.sorelamkopitiam.domain.repository.RewardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RewardsViewModel @Inject constructor(
    private val rewardsRepository: RewardsRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    val rewards: StateFlow<List<RewardItem>> = rewardsRepository.getAllRewards()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val points = rewards.map { rewardsList ->
        rewardsList.sumOf { if (it.isRedeem) -it.points else it.points }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun clearAll() {
        viewModelScope.launch {
            rewardsRepository.clearRewards()
        }
    }

    fun deleteReward(id: Int) {
        viewModelScope.launch {
            rewardsRepository.deleteReward(id)
        }
    }


    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date())
    }
}