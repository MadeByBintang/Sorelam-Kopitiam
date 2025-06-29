package com.example.sorelamkopitiam.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sorelamkopitiam.presentation.screen.cart.CartScreen
import com.example.sorelamkopitiam.presentation.screen.detail.DetailScreen
import com.example.sorelamkopitiam.presentation.screen.home.HomeScreen
import com.example.sorelamkopitiam.presentation.screen.order.OrderScreen
import com.example.sorelamkopitiam.presentation.screen.order.OrderSuccessScreen
import com.example.sorelamkopitiam.presentation.screen.profile.ProfileScreen
import com.example.sorelamkopitiam.presentation.screen.redeem.RedeemScreen
import com.example.sorelamkopitiam.presentation.screen.rewards.RewardsScreen

// --- PERBAIKI TANDA TANGAN FUNGSI ---
fun NavGraphBuilder.mainNavGraph(
    mainNavController: NavHostController,
    rootNavController: NavController
) {
    composable(Screen.Main.Home.route) {
        HomeScreen(navController = mainNavController)
    }
    composable(Screen.Main.Rewards.route) {
        RewardsScreen(navController = mainNavController)
    }
    composable(Screen.Main.Orders.route) {
        OrderScreen()
    }
    composable(Screen.Main.Cart.route) {
        CartScreen(navController = mainNavController)
    }
    // --- PERBAIKI PEMANGGILAN PROFILSCREEN ---
    composable(Screen.Main.Profile.route) {
        ProfileScreen(
            navController = mainNavController, // Untuk navigasi di dalam main_graph
            rootNavController = rootNavController  // Untuk navigasi keluar dari main_graph
        )
    }
    composable(Screen.Main.OrderSuccess.route) {
        OrderSuccessScreen(navController = mainNavController)
    }
    composable(Screen.Main.Redeem.route) {
        RedeemScreen(navController = mainNavController)
    }
    composable(
        route = Screen.Detail.route,
        arguments = listOf(
            navArgument("productId") { type = NavType.IntType },
            navArgument("cartId") {
                type = NavType.IntType
                defaultValue = -1
            }
        )
    ) { backStackEntry ->
        val productId = backStackEntry.arguments?.getInt("productId") ?: -1
        val cartId = backStackEntry.arguments?.getInt("cartId") ?: -1
        DetailScreen(mainNavController, productId, cartId)
    }
}