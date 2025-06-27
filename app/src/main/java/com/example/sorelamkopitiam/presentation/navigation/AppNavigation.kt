package com.example.sorelamkopitiam.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sorelamkopitiam.presentation.screen.core.MainScreen
import com.example.sorelamkopitiam.presentation.screen.splash.SplashEntryPoint

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashEntryPoint()
        }
        composable("main") {
            MainScreen()
        }
    }
}
