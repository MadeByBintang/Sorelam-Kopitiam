package com.example.sorelam.ui.core

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.core.tween
import com.example.sorelam.ui.cart.MyCartScreen
import com.example.sorelam.ui.component.common.BottomNavigationBar
import com.example.sorelam.ui.home.HomeScreen
import com.example.sorelam.ui.order.MyOrderScreen
import com.example.sorelam.ui.order.OrderSuccessScreen
import com.example.sorelam.ui.profile.ProfileScreen
import com.example.sorelam.ui.profile.RewardsScreen


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var selectedIndex by remember { mutableIntStateOf(0) }
    val currentRoute = currentRoute(navController)
    val hideBottomBarRoutes = listOf("cart", "profile", "detail", "order_success")
    val showBottomBar = currentRoute !in hideBottomBarRoutes

    LaunchedEffect(currentRoute) {
        selectedIndex = when (currentRoute) {
            "home" -> 0
            "rewards" -> 1
            "orders", "my_orders" -> 2
            else -> 0
        }
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = fadeIn(animationSpec = tween(durationMillis = 150)) +
                        slideInVertically(animationSpec = tween(durationMillis = 300)) { it },
                exit = fadeOut(animationSpec = tween(durationMillis = 150)) +
                        slideOutVertically(animationSpec = tween(durationMillis = 300)) { it }
            ) {
                BottomNavigationBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = { index ->
                        selectedIndex = index
                        when (index) {
                            0 -> navController.navigate("home") {
                                popUpTo("home") { inclusive = true }
                            }
                            1 -> navController.navigate("rewards") {
                                popUpTo("rewards") { inclusive = true }
                            }
                            2 -> navController.navigate("orders") {
                                popUpTo("orders") { inclusive = true }
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.fillMaxSize()
        ) {
            composable("home") {
                selectedIndex = 0
                HomeScreen(navController)
            }
            composable("rewards") {
                selectedIndex = 1
                RewardsScreen()
            }
            composable("orders") {
                selectedIndex = 2
                MyOrderScreen()
            }
            composable("cart") {
                Scaffold { innerPadding ->
                    MyCartScreen(
                        navController = navController,
                        innerPadding = innerPadding
                    )
                }
            }
            composable("profile") {
                ProfileScreen(navController)
            }
            composable("detail") {
                DetailScreen(navController)
            }
            composable("order_success") {
                OrderSuccessScreen(navController = navController)
            }
            composable("my_orders") {
                selectedIndex = 2
                MyOrderScreen()
            }
        }
    }
}

// Helper untuk mengetahui route saat ini
@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}