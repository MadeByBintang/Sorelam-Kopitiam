package com.example.sorelamkopitiam.presentation.screen.rewards

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sorelamkopitiam.presentation.component.common.PointsCard
import com.example.sorelamkopitiam.presentation.component.topbar.TopBarRewards
import com.example.sorelamkopitiam.presentation.navigation.Screen

@Composable
fun RewardsScreen(
    navController: NavHostController,
    viewModel: RewardsViewModel = hiltViewModel()
) {
    val rewards by viewModel.rewards.collectAsState()
    val points by viewModel.points.collectAsState()


    Column {
        TopBarRewards()
        Spacer(modifier = Modifier.height(16.dp))

        PointsCard(
            points = points,
            onRedeemClick = {
                navController.navigate(Screen.Main.Redeem.route)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        HistoryRewards(histories = rewards)
    }
}