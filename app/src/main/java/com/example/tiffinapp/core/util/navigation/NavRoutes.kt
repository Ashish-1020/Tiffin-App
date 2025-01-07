package com.example.tiffin.core.util.navigation


sealed class NavRoutes(val route: String) {
    object Signup : NavRoutes("signup")
    object Location : NavRoutes("location")
    object Welcome : NavRoutes("welcome")
    object Login : NavRoutes("login")
    object Home : NavRoutes("home/{userId}") {
        fun createRoute(userId: String): String = "home/$userId"
    }
    object Register : NavRoutes("register")
    object MainScreen : NavRoutes("mainscreen")
    object Cart : NavRoutes("cart")
    object Wallet : NavRoutes("wallet")
    object Profile : NavRoutes("profile")
    object OrderDetails : NavRoutes("order_details/{mealId}") {
        fun createRoute(mealId: String) = "order_details/$mealId"
    }
    object MenuScreen : NavRoutes("menuscreen")
    object MyOrders : NavRoutes("my_orders")
}
