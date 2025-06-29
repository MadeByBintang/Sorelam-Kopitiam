package com.example.sorelamkopitiam.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sorelamkopitiam.R
import com.example.sorelamkopitiam.presentation.component.common.PointsCard
import com.example.sorelamkopitiam.presentation.component.home.CoffeeMenu
import com.example.sorelamkopitiam.presentation.component.topbar.TopBar
import com.example.sorelamkopitiam.presentation.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()  // Mengambil state dari ViewModel
    val points by viewModel.points.collectAsState()  // Mengambil points dari ViewModel

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

            PointsCard(
                points = points,
                onRedeemClick = {
                    navController.navigate(Screen.Redeem.route)
                }
            )
            CoffeeMenu(
                menuItems = state.menuItems,  // Mengakses menuItems dari HomeUiState
                onCoffeeClick = { coffeeItem ->
                    navController.navigate(Screen.Detail.createRoute(productId = coffeeItem.id))
                }
            )
        }
    }
}
