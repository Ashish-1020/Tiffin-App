package com.example.tiffinapp.feature.home.presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tiffinapp.home.UI.HomeViewModel

@Composable
fun SearchBar(
    viewModel: HomeViewModel
) {
    val query = viewModel.searchQuery.collectAsState().value

    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = Modifier
                .padding(start = 16.dp, end = 8.dp)
                .weight(1f),
            elevation = CardDefaults.cardElevation(6.dp),
        ) {
            TextField(
                value = query,
                onValueChange = { viewModel.updateSearchQuery(it) },
                placeholder = { Text("What do you want to eat?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(Color.White, RoundedCornerShape(8.dp)),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray,
                    cursorColor = Color.Black
                )
            )
        }
        Card(
            modifier = Modifier
                .padding(start = 8.dp, end = 16.dp)
                .size(55.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF93D8A2)
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        }
    }
}
