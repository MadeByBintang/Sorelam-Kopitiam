package com.example.sorelam.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sorelam.ui.component.common.LoyaltyCard
import com.example.sorelam.ui.component.common.PointsCard
import com.example.sorelam.ui.component.topbar.TopBarRewards

@Composable
fun RewardsScreen() {
    Column {
        TopBarRewards()
        Spacer(modifier = Modifier.height(12.dp))
        LoyaltyCard(current = 4, total = 8)
        PointsCard(points = 2750)
        Spacer(modifier = Modifier.height(18.dp))
        HistoryRewards(histories = rewardHistories)
    }
}