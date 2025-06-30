package com.example.sorelamkopitiam.presentation.screen.core

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sorelamkopitiam.presentation.navigation.BottomNavigationBar
import com.example.sorelamkopitiam.presentation.navigation.Screen
import com.example.sorelamkopitiam.presentation.navigation.mainNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") // -> 2. Tambahkan anotasi ini

@Composable
fun MainScreen(
    // Parameter ini penting untuk navigasi keluar (sign out)
    rootNavController: NavController
) {
    val mainNavController = rememberNavController()
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val hideBottomBarRoutes = listOf(
        Screen.Main.Cart.route,
        Screen.Main.Profile.route,
        Screen.Detail.route,
        Screen.Main.OrderSuccess.route,
        Screen.Main.Redeem.route
    )
    val showBottomBar = !hideBottomBarRoutes.any { currentRoute?.startsWith(it.substringBefore('/')) == true }


    var selectedIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(currentRoute) {
        selectedIndex = when (currentRoute) {
            Screen.Main.Home.route -> 0
            Screen.Main.Rewards.route -> 1
            Screen.Main.Orders.route -> 2
            else -> selectedIndex
        }
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = fadeIn(tween(150)) + slideInVertically(tween(300)) { it },
                exit = fadeOut(tween(150)) + slideOutVertically(tween(300)) { it }
            ) {
                BottomNavigationBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = { index ->
                        val route = when (index) {
                            0 -> Screen.Main.Home.route
                            1 -> Screen.Main.Rewards.route
                            2 -> Screen.Main.Orders.route
                            else -> Screen.Main.Home.route
                        }
                        mainNavController.navigate(route) {
                            popUpTo(mainNavController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        // `innerPadding` yang disediakan oleh Scaffold sekarang diabaikan.
        NavHost(
            navController = mainNavController,
            startDestination = Screen.Main.Home.route,
            // HAPUS MODIFIER PADDING DARI SINI
            // modifier = Modifier.padding(innerPadding)
        ) {
            mainNavGraph(
                mainNavController = mainNavController,
                rootNavController = rootNavController
            )
        }
    }
}