package com.example.sorelamkopitiam.presentation.screen.rewards

import com.example.sorelamkopitiam.domain.model.RewardItem

data class RewardUiState(
    val current: Int = 4,
    val total: Int = 8,
    val points: Int = 2750,
    val histories: List<RewardItem> = emptyList()
)
