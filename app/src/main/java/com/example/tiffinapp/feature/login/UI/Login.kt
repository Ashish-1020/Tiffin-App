
package com.example.tiffin.feature.login.UI

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tiffin.core.data.UserProfile

import com.example.tiffin.core.util.navigation.NavRoutes
import com.example.tiffinapp.R
import com.example.tiffinapp.Screens.BackgroundPattern
import com.example.tiffinapp.feature.login.UI.LoginState
import com.example.tiffinapp.feature.login.UI.LoginViewModel


@ExperimentalMaterial3Api
@Composable
fun LoginAppScreen(navController: NavController,viewModel: LoginViewModel = hiltViewModel()) {
   val loginState by viewModel.loginState.collectAsState()
    val context = LocalContext.current

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B292A))
    ) {
        // Background Image
        BackgroundPattern()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = screenHeight * 0.4f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "Sign-In",
            fontSize = 48.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7DFFA8),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Enter Your Details",
            fontSize = 24.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login UI
        LoginUI(
            onLoginClick = { email, password ->
               viewModel.login(email, password)
            },
            onSignUpClick = { navController.navigate(NavRoutes.Signup.route) },
            onForgotPasswordClick = { /* TODO */ }
        )

    }

    // Handle login states
    // Handle login states
    when (loginState) {
        is LoginState.Idle -> {}
        is LoginState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF2B292A)), // Optional: Add background color
                contentAlignment = Alignment.Center // Align the CircularProgressIndicator to the center
            ) {
                CircularProgressIndicator(color = Color(0xFF93D8A2))
            }
        }
        is LoginState.Success -> {
            val userProfile = (loginState as LoginState.Success).userProfile
            Toast.makeText(LocalContext.current, "Welcome, ${userProfile.name}!", Toast.LENGTH_SHORT).show()
            LaunchedEffect(Unit) {
                navController.navigate("mainscreen") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }
        is LoginState.Error -> {
            Toast.makeText(
                LocalContext.current,
                (loginState as LoginState.Error).errorMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}


@ExperimentalMaterial3Api
@Composable
fun LoginUI(
    onLoginClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Username TextField
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            placeholder = {
                Text(
                    text = "Enter your email",
                    color = Color(0xFF93D8A2)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color(0xFF93D8A2), shape = RoundedCornerShape(4.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF2B292A),
                cursorColor = Color(0xFF7DFFA8),
                focusedIndicatorColor = Color(0xFF7DFFA8),
                unfocusedIndicatorColor = Color.White
            ),
            textStyle = TextStyle(color = Color.White), // Set the entered text color to white
            singleLine = true // Ensure single-line input
        )

// Password TextField
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            placeholder = {
                Text(
                    text = "Enter your password",
                    color = Color(0xFF93D8A2)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color(0xFF93D8A2), shape = RoundedCornerShape(4.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF2B292A),
                cursorColor = Color(0xFF7DFFA8),
                focusedIndicatorColor = Color(0xFF7DFFA8),
                unfocusedIndicatorColor = Color.White
            ),
            textStyle = TextStyle(color = Color.White),
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(
                        painter = painterResource(
                            id = if (passwordVisible.value) R.drawable.visible_eye_svgrepo_com else R.drawable.invisible_svgrepo_com
                        ),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            singleLine = true // Ensure single-line input
        )


        // Forgot Password Text
        Text(
            text = "Forgot Password?",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 8.dp)
                .clickable { onForgotPasswordClick() }
        )

        // Start Session Button
        Button(
            onClick = { onLoginClick(username.value, password.value) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF93D8A2))
        ) {
            Text(
                text = "Start Session",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        // Sign Up Text
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't Have an Account?",
                color = Color.White
            )
            Text(
                text = " SignUp",
                color = Color(0xFF7DFFA8),
                modifier = Modifier.clickable { onSignUpClick() },
                fontWeight = FontWeight.Bold
            )
        }
    }
}

