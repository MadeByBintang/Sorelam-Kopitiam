package com.example.sorelamkopitiam.presentation.screen.redeem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorelamkopitiam.R
import com.example.sorelamkopitiam.domain.model.RedeemItem
import com.example.sorelamkopitiam.domain.repository.RewardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

data class RedeemUiState(
    val points: Int = 0,
    val items: List<RedeemItem> = emptyList()
)


@HiltViewModel
class RedeemViewModel @Inject constructor(
    private val rewardsRepository: RewardsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RedeemUiState())
    val uiState: StateFlow<RedeemUiState> = _uiState

    init {
        loadItems()
        loadPoints()
    }

    private fun loadItems() {
        _uiState.update {
            it.copy(
                items = listOf(
                    RedeemItem(1, "Free Cappuccino", R.drawable.cappuccino, 500, "31 Dec 2025"),
                    RedeemItem(2, "Free Flat White", R.drawable.flat_white, 400, "31 Dec 2025"),
                    RedeemItem(3, "Free Mocha", R.drawable.mocha, 550, "31 Dec 2025")
                )
            )
        }
    }

    private fun loadPoints() {
        viewModelScope.launch {
            val totalPoints = rewardsRepository.getAllRewards()
                .first()
                .sumOf { it.points }
            _uiState.update { it.copy(points = totalPoints) }
        }
    }

    fun redeemItem(item: RedeemItem, onSuccess: () -> Unit, onFail: () -> Unit) {
        if (_uiState.value.points >= item.points) {
            viewModelScope.launch {
                rewardsRepository.insertReward(
                    title = "Redeemed ${item.name}",
                    caption = "Valid until ${item.validUntil}",
                    date = getCurrentDate(),
                    points = -item.points // 🔥 poin dikurangi
                )
                loadPoints()
                onSuccess()
            }
        } else {
            onFail()
        }
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date())
    }
}