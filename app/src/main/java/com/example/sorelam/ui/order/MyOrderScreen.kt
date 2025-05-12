package com.example.sorelam.ui.order

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sorelam.R
import com.example.sorelam.ui.component.topbar.TopBarOrder

@Composable
fun MyOrderScreen() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabTitles = listOf(
        stringResource(id = R.string.tab_on_going),
        stringResource(id = R.string.tab_history)
    )
    val activeColor = Color(0xFF2C6A46)

    Column(modifier = Modifier.fillMaxSize()) {
        TopBarOrder()

        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            contentColor = activeColor,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = activeColor
                )
            },
            containerColor = Color.Transparent
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    selectedContentColor = activeColor,
                    unselectedContentColor = Color.Gray,
                    text = {
                        Text(
                            text = title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> OnGoingOrder()
            1 -> HistoryOrder()
        }
    }
}