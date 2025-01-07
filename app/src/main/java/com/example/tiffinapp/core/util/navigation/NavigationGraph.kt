package com.example.tiffin.core.util.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tiffin.core.domain.AuthRepository
import com.example.tiffin.feature.login.UI.LoginAppScreen
import com.example.tiffinapp.MainScreen
import com.example.tiffinapp.Screens.LocationAppScreen
import com.example.tiffinapp.Screens.TiffinAppScreen
import com.example.tiffinapp.Screens.WelcomeAppScreen
import com.example.tiffinapp.feature.home.presentation.MenuScreen
import com.example.tiffinapp.feature.login.UI.RegisterAppScreen
import com.example.tiffinapp.home.UI.HomeScreen


@ExperimentalMaterial3Api
@Composable
fun NavigationGraph(navController: NavHostController, authRepository: AuthRepository) {
    // Determine the start destination based on login status
    val startDestination = if (authRepository.isUserLoggedIn()) NavRoutes.MainScreen.route else NavRoutes.Signup.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavRoutes.Signup.route) {
            TiffinAppScreen(navController)
        }
        composable(NavRoutes.Location.route) {
            LocationAppScreen(navController)
        }
        composable(NavRoutes.Welcome.route) {
            WelcomeAppScreen(navController)
        }
        composable(NavRoutes.Login.route) {
            LoginAppScreen(navController)
        }
        composable(NavRoutes.Register.route) {
            RegisterAppScreen(navController)
        }
        composable(NavRoutes.Home.route) {
            HomeScreen(navController)
        }
        composable(NavRoutes.MainScreen.route) {
           MainScreen(navController)
        }
        composable(NavRoutes.MenuScreen.route) {
            MenuScreen()
        }


    }
}
