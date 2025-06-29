package com.example.sorelamkopitiam.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sorelamkopitiam.presentation.screen.auth.LoginScreen
import com.example.sorelamkopitiam.presentation.screen.auth.RegisterScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = Screen.Auth.route,
        startDestination = Screen.Auth.Login.route
    ) {
        composable(Screen.Auth.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.Auth.Register.route) {
            RegisterScreen(navController = navController)
        }
    }
}