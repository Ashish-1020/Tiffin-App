package com.example.tiffinapp.feature.cart.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tiffin.feature.cart.data.CartItem
import com.example.tiffin.feature.cart.presentation.CartViewModel
import com.example.tiffin.feature.home.data.Meal
import com.example.tiffinapp.R
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@ExperimentalMaterial3Api
@Composable
fun CartScreen(viewModel: CartViewModel = hiltViewModel()) {
    val cartItems by viewModel.cartItems.collectAsState()
    val mealDetails by viewModel.mealDetails.collectAsState()
    val userProfile = viewModel.userProfile.collectAsState().value

    val showBottomSheet = remember { mutableStateOf(false) }

    // Map `cartItems` to include meal details
    val cartWithMeals = cartItems.mapNotNull { cartItem ->
        val meal = mealDetails.find { it.id == cartItem.mealId }
        meal?.let { cartItem to it }
    }
    val totalCost = cartWithMeals.sumOf { (cartItem, meal) -> cartItem.quantity * meal.price }

    val selectedPaymentOption = remember { mutableStateOf<PaymentOption?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color.White)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.cart_icon),
                    contentDescription = "Cart Icon",
                    modifier = Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Cart",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            items(cartWithMeals.size) { index ->
                val (cartItem, meal) = cartWithMeals[index]
                CartItemView(
                    cartItem = cartItem,
                    meal = meal,
                    onIncrement = { viewModel.incrementQuantity(index) },
                    onDecrement = { viewModel.decrementQuantity(index) }
                )
            }
        }

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Delivery Information",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "\uD83D\uDD8B Edit",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    // Handle the click action here
                    println("Edit clicked!")
                }
            )
        }

        Column {
            // Check if userProfile is not null before displaying user details
            userProfile?.let { profile ->
                // User Name
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile_icon),
                        contentDescription = "Name",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "  ${profile.name}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))

                // User Address
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "Address",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Lda Colony Sector D Lucknow",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))

                // User Phone Number
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = "Phone",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "  ${profile.phoneNumber}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                }
            }
        }

        // Payment Options
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            PaymentOptionButton(
                text = "Pay on Delivery",
                isSelected = selectedPaymentOption.value == PaymentOption.PayOnDelivery,
                onClick = { selectedPaymentOption.value = PaymentOption.PayOnDelivery }
            )
            PaymentOptionButton(
                text = "Wallet Pay",
                isSelected = selectedPaymentOption.value == PaymentOption.PayUsingWallet,
                onClick = { selectedPaymentOption.value = PaymentOption.PayUsingWallet }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    showBottomSheet.value = true
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green)),
                modifier = Modifier.padding(top = 8.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "Cart Icon"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Place Order")
            }

            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = "Subtotal: ₹${totalCost}",
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }


    }
    if (showBottomSheet.value) {
        OrderBottomSheet(onDismiss = { showBottomSheet.value = false })
    }
}

@Composable
fun PaymentOptionButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFF93D8A2) else Color.Gray
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun OrderBottomSheet(onDismiss: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        val currentDateTime = SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.getDefault()).format(
            Date()
        )
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

            if (orderConfirmed.value)
            Image(painter = painterResource(id = R.drawable.tick), contentDescription ="tick", modifier = Modifier.size(40.dp) )
            // Header Text
            Text(
                text = if (orderConfirmed.value) "🎉 Order Successful! 🎉" else "⏳ Placing Your Order...",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF93D8A2)
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (orderConfirmed.value) {
                // Thank You Message
                Text(
                    text = "Thank you for choosing us! 😊",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Service Start Time


                Spacer(modifier = Modifier.height(8.dp))
            } else {
                // Loading Indicator
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    color = Color(0xFF93D8A2)
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

enum class PaymentOption {
    PayOnDelivery,
    PayUsingWallet
}

@Composable
fun CartItemView(
    cartItem: CartItem,
    meal: Meal,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.White, RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = meal.imageUrl),
            contentDescription = meal.name,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = meal.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "₹${meal.price} x ${cartItem.quantity}",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onDecrement) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Remove"
                )
            }
            Text(text = "${cartItem.quantity}", fontWeight = FontWeight.Bold)
            IconButton(onClick = onIncrement) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    }
}



