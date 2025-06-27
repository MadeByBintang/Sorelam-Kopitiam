package com.example.sorelamkopitiam.presentation.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash")
    object Main : Screen("main")

    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object Rewards : Screen("rewards")
    object Orders : Screen("orders")
    object OrderSuccess : Screen("order_success")
    object MyOrders : Screen("my_orders")
    object Redeem : Screen("redeem")

    // 🔥 Dynamic route dengan productId (bukan itemId)
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

//
//    // Placeholder untuk kebutuhan UAS (nanti kamu tinggal aktifkan jika perlu)
//    object Login : Screen("login")
//    object Register : Screen("register")
//    object Settings : Screen("settings")
//    object Search : Screen("search")
//    object CreateItem : Screen("create_item")
//}
