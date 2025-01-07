package com.example.tiffinapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.tiffinapp.feature.cart.presentation.CartScreen
import com.example.tiffinapp.feature.wallet.presentation.WalletScreen
import com.example.tiffinapp.home.UI.HomeScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.res.painterResource
import com.example.tiffin.feature.cart.presentation.CartViewModel
import com.example.tiffin.tif.presentation.components.ProfileScreen


@ExperimentalMaterial3Api
class MainScreenApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
      val navController= rememberNavController()
                MainScreen(navController)

        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun MainScreen(navController: NavController) {
    // State to track the current selected screen
    var selectedTab by remember { mutableStateOf("home") }
    val context= LocalContext.current

    // Scaffold layout with BottomNavigation
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        // Apply innerPadding to the content for proper spacing
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                "home" -> HomeScreen(navController)
                "cart" -> CartScreen()
                "wallet" -> WalletScreen(context)
                "profile"-> ProfileScreen()

            }
        }
    }
}


@SuppressLint("ResourceAsColor")
@Composable
fun BottomNavigationBar(selectedTab: String, onTabSelected: (String) -> Unit) {
    BottomNavigation(
        backgroundColor = Color.White, // Background color
        contentColor = Color(0xFF93D8A2) // Icon and text color
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    tint = if (selectedTab == "home") Color(0xFF93D8A2) else Color.Gray // Set icon color dynamically
                )
            },
            label = {
                Text(
                    text = "Home",
                    color = if (selectedTab == "home") Color(0xFF93D8A2) else Color.Gray // Set label color dynamically
                )
            },
            selected = selectedTab == "home",
            selectedContentColor = Color(0xFF93D8A2),
            unselectedContentColor = Color.Gray,
            onClick = { onTabSelected("home") }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.cart_icon),
                    contentDescription = "Cart",
                    tint = if (selectedTab == "cart") Color(0xFF93D8A2) else Color.Gray // Set icon color dynamically
                )
            },
            label = {
                Text(
                    text = "Cart",
                    color = if (selectedTab == "cart") Color(0xFF93D8A2) else Color.Gray // Set label color dynamically
                )
            },
            selected = selectedTab == "cart",
            selectedContentColor = Color(0xFF93D8A2),
            unselectedContentColor = Color.Gray,
            onClick = { onTabSelected("cart") }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.wallet),
                    contentDescription = "Wallet",
                    tint = if (selectedTab == "wallet") Color(0xFF93D8A2) else Color.Gray // Set icon color dynamically
                )
            },
            label = {
                Text(
                    text = "Wallet",
                    color = if (selectedTab == "wallet") Color(0xFF93D8A2) else Color.Gray // Set label color dynamically
                )
            },
            selected = selectedTab == "wallet",
            selectedContentColor = Color(0xFF93D8A2),
            unselectedContentColor = Color.Gray,
            onClick = { onTabSelected("wallet") }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.profile_icon),
                    contentDescription = "Profile",
                    tint = if (selectedTab == "profile") Color(0xFF93D8A2) else Color.Gray // Set icon color dynamically
                )
            },
            label = {
                Text(
                    text = "Profile",
                    color = if (selectedTab == "profile") Color(0xFF93D8A2) else Color.Gray // Set label color dynamically
                )
            },
            selected = selectedTab == "profile",
            selectedContentColor = Color(0xFF93D8A2),
            unselectedContentColor = Color.Gray,
            onClick = { onTabSelected("profile") }
        )
    }
}



