package com.example.sorelamkopitiam.presentation.screen.splash

import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sorelamkopitiam.presentation.screen.core.MainScreen
import com.example.sorelamkopitiam.presentation.navigation.Screen

@Composable
fun SplashEntryPoint() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}
