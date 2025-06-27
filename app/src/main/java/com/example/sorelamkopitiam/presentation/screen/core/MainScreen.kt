package com.example.sorelamkopitiam.presentation.screen.core

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.sorelamkopitiam.presentation.navigation.BottomNavigationBar
import com.example.sorelamkopitiam.presentation.navigation.Screen
import com.example.sorelamkopitiam.presentation.navigation.MainNavigation
import com.example.sorelamkopitiam.presentation.navigation.currentRoute

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val currentRoute = currentRoute(navController)
    val hideBottomBarRoutes = listOf(
        Screen.Cart.route,
        Screen.Profile.route,
        Screen.Detail.route,
        Screen.OrderSuccess.route,
        Screen.Redeem.route // ✅ Tambahkan ini
    )
    val showBottomBar = currentRoute !in hideBottomBarRoutes

    var selectedIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(currentRoute) {
        selectedIndex = when (currentRoute) {
            Screen.Home.route -> 0
            Screen.Rewards.route -> 1
            Screen.Orders.route -> 2
            else -> 0
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = fadeIn(tween(150)) + slideInVertically(tween(300)) { it },
                exit = fadeOut(tween(150)) + slideOutVertically(tween(300)) { it }
            ) {
                BottomNavigationBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = { index ->
                        selectedIndex = index
                        when (index) {
                            0 -> navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Home.route) { inclusive = true }
                            }
                            1 -> navController.navigate(Screen.Rewards.route) {
                                popUpTo(Screen.Rewards.route) { inclusive = true }
                            }
                            2 -> navController.navigate(Screen.Orders.route) {
                                popUpTo(Screen.Orders.route) { inclusive = true }
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        MainNavigation(
            navController = navController,
            innerPadding = innerPadding
        )
    }
}