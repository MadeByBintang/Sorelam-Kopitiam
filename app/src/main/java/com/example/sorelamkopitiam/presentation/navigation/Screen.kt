package com.example.sorelamkopitiam.presentation.navigation

sealed class Screen(val route: String) {

    // --- TAMBAHKAN OBJECT DI BAWAH INI ---
    object Splash : Screen("splash")

    // Grafik untuk alur otentikasi
    object Auth : Screen("auth_graph") {
        object Login : Screen("login")
        object Register : Screen("register")
    }

    // Grafik untuk alur utama aplikasi setelah login
    object Main : Screen("main_graph") {
        object Home : Screen("home")
        object Cart : Screen("cart")
        object Profile : Screen("profile")
        object Rewards : Screen("rewards")
        object Orders : Screen("orders")
        object OrderSuccess : Screen("order_success")
        object Redeem : Screen("redeem")
    }

    // Rute yang bisa diakses dari mana saja
    object Detail : Screen("detail/{productId}?cartId={cartId}") {
        fun createRoute(productId: Int, cartId: Int? = null): String {
            return if (cartId != null) {
                "detail/$productId?cartId=$cartId"
            } else {
                "detail/$productId"
            }
        }
    }
}