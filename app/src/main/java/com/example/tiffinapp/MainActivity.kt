package com.example.tiffinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tiffin.core.domain.AuthRepository
import com.example.tiffin.core.util.navigation.NavigationGraph
import com.example.tiffinapp.Screens.TiffinAppScreen
import com.example.tiffinapp.feature.wallet.presentation.WalletScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var authRepository: AuthRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavigationGraph(navController, authRepository)

            }
        }
    }

