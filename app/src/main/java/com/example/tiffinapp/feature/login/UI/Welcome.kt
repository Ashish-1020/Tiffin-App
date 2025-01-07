package com.example.tiffinapp.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class Welcome : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WelcomeApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWelcomeAppScreen() {
    WelcomeApp()
}

@Composable
fun WelcomeApp(){
    val navController = rememberNavController()
   WelcomeAppScreen(navController = navController)
}

@Composable
fun  WelcomeAppScreen(navController: NavHostController){
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF2B292A))
    ) {

        // Background Image
        BackgroundPattern()
    }




        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            // Rider Background (Centered Below the Background Image)
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = screenHeight * 0.4f)
                    .align(Alignment.CenterHorizontally)
                // Adjust this padding to move the RiderBackground down as needed
            ) {
                Text(
                    text = "Welcome",
                    fontSize = 64.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7DFFA8),
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = screenHeight * 0.1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title text
                Text(
                    text = "Discover your next\nfavorite meal with us.",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Login Button
                Button(
                    onClick = {
                        // Handle login click
                        navController.navigate("login")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7DFFA8)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "LOGIN",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sign Up Text
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No account?",
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Sign Up",
                        color = Color(0xFF7DFFA8),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            // Navigate to Sign-Up Screen
                            navController.navigate("register")
                        }
                    )
                }
            }

        }

    // Place Indicator Dots and Next Button
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Indicator Dots at Bottom Left
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
        ) {
            IndicatorDots2()
        }



    }


    }


@Composable
fun IndicatorDots2() {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color = Color.Gray, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color = Color.Gray, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .height(12.dp)
                .width(20.dp)
                .background(color = Color(0xFF7DFFA8), shape = RoundedCornerShape(8.dp))
        )




    }
}