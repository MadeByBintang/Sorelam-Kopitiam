package com.example.sorelamkopitiam.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sorelamkopitiam.presentation.screen.core.MainScreen
import com.example.sorelamkopitiam.presentation.screen.splash.SplashScreen

@Composable
fun AppNavigation(
    viewModel: AppNavigationViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val postSplashDestination = if (viewModel.isLoggedIn()) {
        Screen.Main.route
    } else {
        Screen.Auth.route
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(postSplashDestination) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        authNavGraph(navController = navController)

        composable(route = Screen.Main.route) {
            MainScreen(rootNavController = navController)
        }
    }
}