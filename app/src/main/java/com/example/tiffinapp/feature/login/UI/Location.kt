package com.example.tiffinapp.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import androidx.navigation.compose.rememberNavController
import com.example.tiffinapp.R

class Location : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationApp()
        }

    }
}

@Composable
fun NavigationApp() {
    val navController = rememberNavController()

   LocationAppScreen(navController = navController)

}

@Preview(showBackground = true)
@Composable
fun PreviewLocationAppScreen() {
    NavigationApp()

}

@Composable
fun getScreenDimensions(): Pair<Float, Float> {
    val configuration = LocalConfiguration.current
    return Pair(configuration.screenWidthDp.toFloat(), configuration.screenHeightDp.toFloat())
}

@Composable
fun LocationAppScreen(navController: NavHostController) {
    val (screenWidth, screenHeight) = getScreenDimensions()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B292A))
    ) {

        // Background Image
        BackgroundPattern()

        // Rider Background (Centered Below the Background Image)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = screenHeight.dp * 0.5f)
                .padding(start = screenWidth.dp * 0.15f)
                 // Adjust this padding to move the RiderBackground down as needed
        ) {
            RiderBackground()
        }



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = screenHeight.dp * 0.65f)
                .padding(10.dp)) {
              FullWidthButtons()
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
                IndicatorDots1()
            }

            // Next Button at Bottom Right
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            ) {
                NextButton(onClick = {
                    navController.navigate("welcome")
                })
            }
        }
    }
}

@Composable
fun IndicatorDots1() {
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
                .height(12.dp)
                .width(20.dp)
                .background(color = Color(0xFF7DFFA8), shape = RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color = Color.Gray, shape = CircleShape)
        )
    }
}

@Composable
fun RiderBackground(){
    val (screenWidth, screenHeight) = getScreenDimensions()


    Box(
        modifier = Modifier
            .width(screenWidth.dp * 0.75f)
            .height(screenHeight.dp * 0.15f)
            // Adjust height to fit background
    ) {
        Image(
            painter = painterResource(id = R.drawable.location_background), // Replace with actual drawable
            contentDescription = "Background Pattern",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun FullWidthButtons() {
    val (screenWidth, screenHeight) = getScreenDimensions()
    Column(
        modifier = Modifier.run {
            fillMaxWidth()
                .padding(16.dp)
                        },
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // First Button

        Button(
            onClick = { /* Handle click */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(screenWidth.dp * 0.12f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7DFFA8)), // Set background color
            shape = RoundedCornerShape(16.dp) // Rounded corners
        ) {
            Text(
                text = "Enable Device Location",
                color = Color.Black, // Text color
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Second Button
        Button(
            onClick = { /* Handle click */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(screenWidth.dp * 0.12f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Set background color
            shape = RoundedCornerShape(16.dp) // Rounded corners
        ) {
            Text(
                text = "Enter your Location Manually",
                color = Color.White, // Text color
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/*@Composable
fun BackgroundPattern1() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight * 0.4f) // Adjust height to fit background
    ) {
        Image(
            painter = painterResource(id = R.drawable.background), // Replace with actual drawable
            contentDescription = "Background Pattern",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
*/