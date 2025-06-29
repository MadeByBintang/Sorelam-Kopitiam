package com.example.sorelamkopitiam.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sorelamkopitiam.R
import com.example.sorelamkopitiam.presentation.component.common.LoyaltyCard
import com.example.sorelamkopitiam.presentation.component.home.CoffeeMenu
import com.example.sorelamkopitiam.presentation.component.topbar.TopBar
import com.example.sorelamkopitiam.presentation.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TopBar(
                userName = stringResource(id = R.string.user_name_anderson),
                onCartClick = { navController.navigate(Screen.Cart.route) },
                onProfileClick = { navController.navigate(Screen.Profile.route) }
            )

            LoyaltyCard() // ✅ Sudah otomatis dari database

            CoffeeMenu(
                menuItems = state.menuItems,
                onCoffeeClick = { coffeeItem ->
                    navController.navigate(Screen.Detail.createRoute(productId = coffeeItem.id))
                }
            )
        }
    }
}
