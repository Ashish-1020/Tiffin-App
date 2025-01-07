package com.example.tiffinapp.feature.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tiffin.feature.home.data.Meal
import com.google.accompanist.systemuicontroller.rememberSystemUiController

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextAlign
import com.example.tiffinapp.R

import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(viewModel: MenuViewModel = hiltViewModel()) {
    val meals = viewModel.meals.collectAsState().value
    val totalCost = viewModel.totalCost.collectAsState().value
    val isVegOnly = viewModel.isVegMode.collectAsState().value
    val filteredMeals = if (isVegOnly) meals.filter { it.mealtype == "Veg" } else meals

    var selectedDuration by remember { mutableStateOf("1 Week") }
    var showBottomSheet by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()

    if (showBottomSheet) {
        OrderBottomSheet(
            onDismiss = { showBottomSheet = false }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Weekly Menu",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (isVegOnly) "Veg Mode" else "Non-Veg Mode",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isVegOnly) Color.Green else Color.Gray
                )
                Switch(
                    checked = isVegOnly,
                    onCheckedChange = { viewModel.toggleMode() },
                    modifier = Modifier.size(50.dp),
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = if (isVegOnly)  Color(0xFF93D8A2) else Color.Gray
                    )
                )
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filteredMeals.groupBy { it.day }.forEach { (day, mealsForDay) ->
                item {
                    Text(
                        text = day,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(mealsForDay.size) { index ->
                    MealItem(meal = mealsForDay[index])
                }
            }
        }

        Divider(color = Color.Gray, thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total Cost", style = MaterialTheme.typography.titleMedium)
            Text(
                text = "₹${String.format("%.2f", totalCost)}",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

        // Subscription Duration Selection
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            listOf("1 Week", "1 Month", "3 Months").forEach { duration ->
                Button(
                    onClick = { selectedDuration = duration },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedDuration == duration)  Color(0xFF93D8A2)else Color.Gray
                    )
                ) {
                    Text(text = duration, color = Color.White)
                }
            }
        }

        // Start Service Button
        Button(
            onClick = { showBottomSheet = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor =  Color(0xFF93D8A2))
        ) {
            Icon(painter = painterResource(id = R.drawable.wallet), contentDescription = "wallet")
            Text(text = "Start Service Using Wallet", color = Color.White)
        }
    }
}@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderBottomSheet(onDismiss: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.background // Set the background color to white or use custom color
    ) {
        val currentDateTime = SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.getDefault()).format(Date())
        val orderConfirmed = remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            delay(2000) // Simulate order processing
            orderConfirmed.value = true
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Text
            Text(
                text = if (orderConfirmed.value) "🎉 Order Successful! 🎉" else "⏳ Placing Your Order...",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (orderConfirmed.value) {
                // Thank You Message
                Text(
                    text = "Thank you for choosing us! 😊\n" +
                            "The amount will be deducted from your wallet after delivery for a seamless experience.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Service Start Time
                Text(
                    text = "📅 Service Started: $currentDateTime",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Success Icon
               /* Image(
                    painter = painterResource(id = R.drawable.tick), // Replace with your animation resource
                    contentDescription = "Order Successful",
                    modifier = Modifier.size(100.dp)
                )*/
            } else {
                // Loading Indicator
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Your order is being processed.\nPlease hold on a moment!",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun MealItem(meal: Meal) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Meal image
            Image(
                painter = painterResource(id = meal.imageUrl),
                contentDescription = meal.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Meal details
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${meal.calories} calories | ${meal.dietarylabel}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    text = meal.mealtype,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Meal offer badge
            if (meal.offer.isNotBlank()) {
                Text(
                    text = "₹" + meal.price.toString(),
                    style = MaterialTheme.typography.labelSmall.copy(color = Color.White),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .background(
                            color = Color(0xFF93D8A2),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}
