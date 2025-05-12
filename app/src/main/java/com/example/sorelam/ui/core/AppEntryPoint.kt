package com.example.sorelam.ui.core

import androidx.compose.runtime.*
import androidx.navigation.compose.*
import kotlinx.coroutines.delay
import androidx.navigation.compose.composable


@Composable
fun AppEntryPoint() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            var showSplash by remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                delay(2000)
                showSplash = false
                navController.navigate("main") {
                    popUpTo("splash") { inclusive = true }
                }
            }

            if (showSplash) {
                SplashScreen()
            }
        }

        composable("main") {
            MainScreen()
        }
    }
}