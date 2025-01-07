/*package com.example.tiffin.tif.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiffin.R

@Composable
fun FoodDetailsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD9D9D9))
    ) {
        // Top Section with Back Button and Title
        Column(
            modifier = Modifier
                .padding(top = 40.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    modifier = Modifier
                        .size(70.dp)
                        .weight(1f),
                    onClick = { /* Handle back */ }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.back_arrow),
                        modifier = Modifier
                            .size(70.dp),
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "FRENCH",
                    color = colorResource(R.color.green),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 32.dp)
                        .weight(1f),
                    letterSpacing = 3.sp
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = "GREEN SALAD",
                color = Color.Black,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Image Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.dish_bowl),
                contentDescription = "Salad",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(230.dp)
                    .clip(CircleShape)
            )
        }

        // Details Section
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            shape = RoundedCornerShape(
                topStart = 40.dp,
                topEnd = 40.dp,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            )
        ) {
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp, start = 12.dp, end = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "French Green Salad",
                                fontSize = 25.sp,
                                color = Color.Black
                            )
                            Card(
                                modifier = Modifier,
                                shape = RoundedCornerShape(4.dp),
                                colors = CardDefaults.cardColors(colorResource(R.color.green))
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(vertical = 6.dp, horizontal = 10.dp),
                                    text = "Breakfast",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontSize = 15.sp
                                    )
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                        ) {
                            Text(
                                text = "Unleash the power of green in\nevery bite with a mouthwatering\ngreen sandwich...",
                                fontSize = 15.sp,
                                color = Color.Gray,
                                overflow = TextOverflow.Ellipsis
                            )
//                            Button(
//                                onClick = { /* Handle read more */ },
//                                colors = ButtonDefaults.buttonColors(
//                                    containerColor = Color.Transparent
//                                ),
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .align(Alignment.Bottom)
//                                    .offset((-125).dp, 15.dp)
//                            ) {
//                                Text(
//                                    text = "Read More",
//                                    color = colorResource(R.color.green),
//                                    fontSize = 15.sp,
//                                    fontWeight = FontWeight.Bold,
//                                )
//                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                for (i in 1..5) {
                                    Icon(
                                        painter = if (i <= 4) {
                                            painterResource(R.drawable.star)
                                        } else painterResource(R.drawable.star_outline),
                                        contentDescription = "Rating Star",
                                        tint = colorResource(R.color.green),
                                        modifier = Modifier
                                            .size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "4.0",
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }

                item {
                    // Delivery Amount and Total Section
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(colorResource(R.color.green))
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Column {
                                Text(
                                    modifier = Modifier,
                                    text = "Delivery Amount",
                                    fontSize = 22.sp,
                                    style = TextStyle(),
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(30.dp))
                                Text(
                                    text = "Total Amount",
                                    fontSize = 15.sp,

                                    )
                                Text(
                                    modifier = Modifier
                                        .padding(top = 4.dp),
                                    text = "Rs 140.00",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(Modifier.width(16.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Card(
                                    modifier = Modifier
                                        .padding(start = 0.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White.copy(alpha = 0.6f)
                                    ),
                                    border = BorderStroke(width = 1.dp, color = Color.White)
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(vertical = 8.dp, horizontal = 16.dp)
                                            .align(Alignment.CenterHorizontally),
                                        text = "Zero",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.Black
                                    )
                                }
                                Spacer(Modifier.height(26.dp))
                                Button(
                                    onClick = { /* Handle add to cart */ },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.White.copy(alpha = 0.6f)
                                    ),
                                    border = BorderStroke(1.dp, Color.White)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.add_to_cart),
                                        contentDescription = "Add to cart",
                                        Modifier.size(25.dp),
                                        tint = colorResource(R.color.green)
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Text(
                                        text = "Add to cart",
                                        color = Color.Black,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }

                item {
                    // Payment Section
                    Button(
                        onClick = { /* Handle payment */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2B292A)
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Make Payment",
                                color = Color.White,
                                fontSize = 25.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = { /* Handle arrow action */ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White)
                                ,
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.forward_arrow),
                                    modifier = Modifier
                                        .size(40.dp),
                                    contentDescription = "Proceed"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFoodDetailsScreen() {
    FoodDetailsScreen()
}
*/