@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.tiffinapp.feature.login.UI

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import com.example.tiffinapp.R
import com.example.tiffinapp.Screens.BackgroundPattern
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController



class Register: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRegisterAppScreen() {
    RegisterScreen()
}
@Composable
fun RegisterScreen(){
    val navController = rememberNavController()
    RegisterAppScreen(navController = navController)
}
@Composable
fun RegisterAppScreen(navController: NavHostController,viewModel: RegisterViewModel= hiltViewModel()

) {
    var fullName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF2B292A))
    ) {
        // Background Pattern
        BackgroundPattern()

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Title
            Text(
                text = "Sign-up",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7DFFA8),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter your data",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input Fields
            // Full Name Field
            Box(modifier = Modifier.fillMaxWidth()){
                Text(
                    text = "Full Name",
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                value = fullName,
                onValueChange = { fullName = it },
                placeholder = { Text("David Hilt", color = Color(0xFF93D8A2)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .border(1.dp, Color(0xFF93D8A2), shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF2B292A),
                    cursorColor = Color(0xFF7DFFA8),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(color = Color.White), // Ensure entered text is white
                singleLine = true // Single-line input
            )

            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Email",
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("tiffin@gmail.com", color = Color(0xFF93D8A2)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .border(1.dp, Color(0xFF93D8A2), shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF2B292A),
                    cursorColor = Color(0xFF7DFFA8),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(color = Color.White), // Ensure entered text is white
                singleLine = true // Single-line input
            )

            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Mobile Number",
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            TextField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                placeholder = { Text("9876543210", color = Color(0xFF93D8A2)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .border(1.dp, Color(0xFF93D8A2), shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF2B292A),
                    cursorColor = Color(0xFF7DFFA8),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(color = Color.White), // Ensure entered text is white
                singleLine = true // Single-line input
            )

            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Password",
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Enter your password", color = Color(0xFF93D8A2)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFF93D8A2),
                        shape = RoundedCornerShape(8.dp)
                    ),
                colors = TextFieldDefaults.textFieldColors(

                    containerColor = Color(0xFF2B292A),
                    cursorColor = Color(0xFF7DFFA8),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(color = Color.White), // Ensure entered text is white
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        val icon = if (passwordVisible) R.drawable.visible_eye_svgrepo_com else R.drawable.invisible_svgrepo_com
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                singleLine = true // Single-line input
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Terms and Conditions
            Text(
                text = "By continuing, you agree to\nTerms of Use and Privacy Policy.",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Start Session Button
            Button(
                onClick = {

                   viewModel.registerUser(
                        name = fullName,
                        email = email,
                        password = password,
                        phoneNumber = mobileNumber,
                        dateOfBirth = dateOfBirth.takeIf { it.isNotBlank() })
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7DFFA8)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Start Session", color = Color.Black, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Social Login
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "or sign up with", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painterResource(id = R.drawable.google_color_svgrepo_com),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF7DFFA8),
                            shape = RoundedCornerShape(8.dp)
                        )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painterResource(id = R.drawable.facebook_svgrepo_com__1_),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF7DFFA8),
                            shape = RoundedCornerShape(8.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Login Redirect
            TextButton(onClick = { /* Navigate to Login */ }) {
                Text(text = "Already have an account? Log in", color = Color(0xFF93D8A2))
            }
        }


        when (uiState) {
            is RegisterUiState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = Color.White) // Progress bar
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Loading...", color = Color.White)
                }
            }
            is RegisterUiState.Success -> {
                val userProfile = (uiState as RegisterUiState.Success).userProfile
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Welcome, ${userProfile.name}!", color = Color.Green, fontSize = 20.sp)
                }
                navController.navigate("mainscreen")
            }
            is RegisterUiState.Error -> {
                val errorMessage = (uiState as RegisterUiState.Error).message
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = errorMessage, color = Color.Red, fontSize = 18.sp)
                }
            }
            else -> {
                // Handle other states if needed
            }
        }
    }
}
