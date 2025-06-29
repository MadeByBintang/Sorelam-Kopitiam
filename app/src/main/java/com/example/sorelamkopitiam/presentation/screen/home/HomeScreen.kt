package com.example.sorelamkopitiam.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sorelamkopitiam.presentation.component.common.PointsCard
import com.example.sorelamkopitiam.presentation.component.home.CoffeeMenu
import com.example.sorelamkopitiam.presentation.component.topbar.TopBar
import com.example.sorelamkopitiam.presentation.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val points by viewModel.points.collectAsState()
    // Ambil data user dari ViewModel
    val user by viewModel.userState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TopBar(
                // Tampilkan nama pengguna, atau "Guest" jika belum ter-load
                userName = user?.username ?: "Guest",
                onCartClick = { navController.navigate(Screen.Main.Cart.route) },
                onProfileClick = { navController.navigate(Screen.Main.Profile.route) }
            )

            PointsCard(
                points = points,
                onRedeemClick = {
                    navController.navigate(Screen.Main.Redeem.route)
                }
            )
            CoffeeMenu(
                menuItems = state.menuItems,
                onCoffeeClick = { coffeeItem ->
                    navController.navigate(Screen.Detail.createRoute(productId = coffeeItem.id))
                }
            )
        }
    }
}