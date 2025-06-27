package com.example.sorelamkopitiam.presentation.screen.order

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sorelamkopitiam.presentation.component.topbar.TopBarOrder
import com.example.sorelamkopitiam.presentation.screen.order.component.HistoryOrderList
import com.example.sorelamkopitiam.presentation.screen.order.component.OnGoingOrderList

@Composable
fun OrderScreen(
    viewModel: OrderViewModel = hiltViewModel()
) {
    val selectedTab by viewModel.selectedTab.collectAsState()
    val ongoingOrders by viewModel.ongoingOrders.collectAsState()
    val historyOrders by viewModel.historyOrders.collectAsState()

    Scaffold(
        topBar = { TopBarOrder() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 12.dp)
        ) {
            // ✅ Tab dengan background mengikuti screen
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.Transparent,
                contentColor = Color(0xFF007042),
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .tabIndicatorOffset(tabPositions[selectedTab])
                            .height(3.dp),
                        color = Color(0xFF007042)
                    )
                },
                divider = {}
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { viewModel.selectTab(0) },
                    text = {
                        Text(
                            "On Going",
                            color = if (selectedTab == 0) Color(0xFF007042) else Color.Gray
                        )
                    }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { viewModel.selectTab(1) },
                    text = {
                        Text(
                            "History",
                            color = if (selectedTab == 1) Color(0xFF007042) else Color.Gray
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            when (selectedTab) {
                0 -> OnGoingOrderList(
                    orders = ongoingOrders,
                    onComplete = { order -> viewModel.updateOrderToHistory(order) }
                )
                1 -> HistoryOrderList(
                    orders = historyOrders,
                    onDelete = { viewModel.deleteHistoryOrder(it) }
                )
            }
        }
    }
}