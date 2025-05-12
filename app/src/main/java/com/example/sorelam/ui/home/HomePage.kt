package com.example.sorelam.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sorelam.R
import com.example.sorelam.ui.component.common.LoyaltyCard
import com.example.sorelam.ui.component.topbar.TopBar

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                TopBar(
                    userName = stringResource(id = R.string.user_name_anderson),
                    onCartClick = { navController.navigate("cart") },
                    onProfileClick = { navController.navigate("profile") }
                )
                LoyaltyCard(current = 4, total = 8)
                Spacer(modifier = Modifier.height(30.dp))
                CoffeeMenu(onCoffeeClick = {
                    navController.navigate("detail")
                })
            }
        }
    }
}