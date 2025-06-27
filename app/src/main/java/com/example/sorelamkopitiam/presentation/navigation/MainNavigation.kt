package com.example.sorelamkopitiam.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sorelamkopitiam.presentation.screen.cart.CartScreen
import com.example.sorelamkopitiam.presentation.screen.cart.CartViewModel
import com.example.sorelamkopitiam.presentation.screen.detail.DetailScreen
import com.example.sorelamkopitiam.presentation.screen.home.HomeScreen
import com.example.sorelamkopitiam.presentation.screen.order.OrderScreen
import com.example.sorelamkopitiam.presentation.screen.order.OrderSuccessScreen
import com.example.sorelamkopitiam.presentation.screen.profile.ProfileScreen
import com.example.sorelamkopitiam.presentation.screen.redeem.RedeemScreen
import com.example.sorelamkopitiam.presentation.screen.rewards.RewardsScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    val cartViewModel: CartViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Rewards.route) {
            RewardsScreen(navController = navController) // ✅ Kirim navController ke RewardsScreen
        }
        composable(Screen.Orders.route) {
            OrderScreen()
        }
        composable(Screen.Cart.route) {
            CartScreen(
                navController = navController,
                cartViewModel = cartViewModel // ✅ ganti param jadi 'viewModel'
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType },
                navArgument("cartId") {
                    type = NavType.IntType
                    defaultValue = -1 // -1 artinya tidak ada cartId (add mode)
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: -1
            val cartId = backStackEntry.arguments?.getInt("cartId") ?: -1
            DetailScreen(navController, productId, cartId)
        }

        composable(Screen.OrderSuccess.route) {
            OrderSuccessScreen(navController = navController)
        }

        composable(Screen.Redeem.route) {
            RedeemScreen(navController = navController)
        }
    }
}