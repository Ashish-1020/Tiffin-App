package com.example.tiffin.tif.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.tiffinapp.R

@Composable
fun ProfileScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.green))
            .padding(top = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // Top AppBar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "My profile",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFFF8F8F8),
                    modifier = Modifier
                        .padding(start = 75.dp),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

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
                modifier = Modifier
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF8F8F8)
                )
            ) {
                // Profile Picture
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profileimage), // Replace with your image
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(130.dp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.camera_svgrepo_com),
                        contentDescription = "Edit Profile Picture",
                        tint = Color(0xFFF8F8F8),
                        modifier = Modifier
                            .size(27.dp)
                            .offset(6.dp, 6.dp)
                            .background(colorResource(R.color.green), RoundedCornerShape(10.dp))
                            .padding(3.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Form Fields
                ProfileField(label = "Full Name", value = "Rishi")
                ProfileField(label = "Date of Birth", value = "09 / 10 / 1991")
                ProfileField(label = "Email", value = "rishi@example.com")
                ProfileField(label = "Phone Number", value = "+91 9876543211")

                Spacer(modifier = Modifier.height(20.dp))

                // Update Profile Button
                Button(
                    onClick = { /* Handle Update Profile */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.green)
                    ),
                    border = BorderStroke(1.dp, Color(0xFF5B9367)),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Update Profile",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileField(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 32.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = Color(0xFF391713) // Label Color
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.green),
                    RoundedCornerShape(8.dp)
                )
                .padding(vertical = 12.dp, horizontal = 20.dp)
        )
    }
}



@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}