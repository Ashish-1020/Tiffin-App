package com.example.tiffinapp.home.UI

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tiffin.feature.home.data.Meal
import com.example.tiffin.feature.home.data.MenuItem
import com.example.tiffinapp.R
import com.example.tiffinapp.feature.home.presentation.Components.CategoriesRow
import com.example.tiffinapp.feature.home.presentation.Components.MenuList
import com.example.tiffinapp.feature.home.presentation.Components.SearchBar
import com.example.tiffinapp.feature.home.presentation.Components.TopBar
import com.example.tiffinapp.feature.login.UI.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController,viewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current

    // Collect the menu items and Veg mode state from the view model
    val mealItems by viewModel.menuItems.collectAsState()
    val isVegMode by remember { viewModel.isVegMode }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            Spacer(Modifier.height(20.dp))
        }

        item {
            // Top bar with location and toggles
            TopBar(viewModel)
        }

        item {
            // Search bar - pass the ViewModel directly
            SearchBar(viewModel = viewModel)
        }

        item {
            // Categories row
            CategoriesRow { categoryName ->
                // Example: Show a Toast for the clicked category

               if(categoryName=="Weekly Menu"){
                   navController.navigate("menuscreen")
               }
            }
        }

        item {
            // Veg Mode Toggle and Menu list
            Column {
                MenuList(context,mealItems,viewModel)
            }
        }
    }
}




