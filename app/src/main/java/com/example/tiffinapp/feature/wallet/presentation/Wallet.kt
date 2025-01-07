package com.example.tiffinapp.feature.wallet.presentation

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import com.example.tiffinapp.PaymentActivity

import com.example.tiffinapp.R
import kotlinx.coroutines.delay
@ExperimentalMaterial3Api
@Composable
fun WalletScreen(context: Context) {
    val isAddingMoney = remember { mutableStateOf(false) }
    val amountToAdd = remember { mutableStateOf("") }
    val currentBalance = remember { mutableStateOf(500.0) } // Initial balance
    val triggerAddMoney = remember { mutableStateOf(false) } // Trigger state for the delay

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.green))
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Wallet",
                    modifier = Modifier
                        .padding(start = 60.dp, top = 30.dp),
                    style = MaterialTheme.typography.displaySmall.copy(
                        color = Color(0xFFF8F8F8),
                        fontSize = 50.sp
                    ),
                    fontWeight = FontWeight.Bold
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = 32.dp, end = 32.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile_icon),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Active",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Light,
                            fontSize = 15.sp
                        ),
                        color = Color.Black
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Card(
                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                ),
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF8F8F8)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    if (isAddingMoney.value) {
                        Column {
                            TextField(
                                value = amountToAdd.value,
                                onValueChange = { amountToAdd.value = it },
                                label = { Text("Enter Amount") },
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color(0xFFE7F6EB),
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    cursorColor = Color(0xFF93D8A2)
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFE7F6EB), shape = RoundedCornerShape(8.dp)),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number
                                )
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = {
                                    triggerAddMoney.value = true
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF93D8A2)
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Add Amount", color = Color.White)
                            }


                            Spacer(modifier = Modifier.height(24.dp))
                            ButtonRow(title = "Refer & Earn", icon = Icons.Default.ArrowForward) {
                                // Call ViewModel's method
                            }
                            Spacer(modifier = Modifier.height(24.dp))
                            SettingsMenu()
                        }
                    } else {
                        Text(
                            text = "Balance",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 32.sp
                        )
                        Text(
                            text = "Rs.${currentBalance.value}",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 32.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        ButtonRow(title = "Add Money", icon = Icons.Default.ArrowForward) {
                            isAddingMoney.value = true
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        ButtonRow(title = "Refer & Earn", icon = Icons.Default.ArrowForward) {
                            // Call ViewModel's method
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        SettingsMenu()
                    }
                }
            }
        }
    }

    // LaunchedEffect for handling delay after the button is clicked
    LaunchedEffect(triggerAddMoney.value) {
        if (triggerAddMoney.value) {
            delay(2000) // Delay of 2 seconds
            val amount = amountToAdd.value.toDoubleOrNull() ?: 0.0
            if (amount > 0.0) {
                currentBalance.value += amount // Add to current balance
            }

            // Navigate to PaymentActivity with the amount
            val intent = Intent(context, PaymentActivity::class.java).apply {
                putExtra("amount", amountToAdd.value)
            }
            context.startActivity(intent)

            // Reset trigger state to avoid re-triggering
            triggerAddMoney.value = false
        }
    }
}



@Composable
fun ButtonRow(title: String, icon: ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF000000)
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = Color.White,
            fontSize = 25.sp,
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        )
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF393F3A)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_arrow_right_foreground),
                modifier = Modifier
                    .size(width = 40.dp, height = 25.dp),
                contentDescription = "Proceed"
            )
        }
    }
}

@Composable
fun TransactionItem(title: String, subtitle: String, amount: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 17.sp
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 13.sp,
                    color = Color(0xFFBDBDBD)
                )
            }
            val color = if (subtitle == "Credit") colorResource(R.color.green) else Color.Red
            val amounttxt:String= if (subtitle == "Credit") "+$amount" else "-$amount"
            Text(
                text = amounttxt,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 17.sp,
                color =color
            )

        }
    }
}



data class Transaction(
    val id: String = "",
    val amount: Double = 0.0,
    val type: String = "", // e.g., "Credit" or "Debit"
    val timestamp: Long = System.currentTimeMillis(), // Default to current time
    val description: String = "" // Default to an empty string
)


data class Wallet(
    val id: String = "",                // Default value
    val amount: Double = 0.0,           // Default value
    val transactions: List<Transaction> = emptyList() // Default value
)


@Composable
fun SettingsMenu() {
    val menuItems = listOf(
        MenuItem("payment settings", R.drawable.settings_svgrepo_com, Color(0xFFFF6464)),
        MenuItem("transaction history", R.drawable.history_svgrepo_com, Color(0xFF64B5FF)),
        MenuItem("get help", R.drawable.help_svgrepo_com, Color(0xFFFFB564)),
        MenuItem("voice pay", R.drawable.mic_svgrepo_com, Color(0xFF64FF64))
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F7F7))
            .padding(top = 4.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        menuItems.forEach { item ->
            MenuRow(item)
        }
    }
}

@Composable
fun MenuRow(menuItem: MenuItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(4.dp)
            .clickable { /* Handle click */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(menuItem.iconBackgroundColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = menuItem.iconRes),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = menuItem.title,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

data class MenuItem(
    val title: String,
    val iconRes: Int,
    val iconBackgroundColor: Color
)

@Preview(showBackground = true)
@Composable
fun SettingsMenuPreview() {
    SettingsMenu()
}
