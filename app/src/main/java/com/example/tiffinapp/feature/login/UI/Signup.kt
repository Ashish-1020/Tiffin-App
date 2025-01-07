package com.example.tiffinapp.Screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tiffin.core.domain.AuthRepository
import com.example.tiffin.core.util.navigation.NavigationGraph


import com.example.tiffinapp.R

 class Signup : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            TiffinApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTiffinAppScreen() {
    TiffinApp()
}


@Composable
fun TiffinApp() {
    val navController = rememberNavController()
    TiffinAppScreen(navController = navController)
}

@Composable
fun TiffinAppScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B292A))
    ) {
        // Background Image
        BackgroundPattern()

        // Centered Content (Title)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 150.dp)
            ) {
                Text(
                    text = "TIFFIN",
                    fontSize =54.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7DFFA8),
                    textAlign = TextAlign.Center
                )
            }

            // Title Text
            Box(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 6.dp)
                    .padding(start = 16.dp)// Adjust spacing
            ) {
                GreenText()
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
                IndicatorDots()
            }

            // Next Button at Bottom Right
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            ) {
                NextButton(onClick = {
                    navController.navigate("location") // Navigate to Location screen
                })
            }
        }
    }
}

@Composable
fun BackgroundPattern() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight * 0.5f) // Adjust height to fit background
    ) {
        Image(
            painter = painterResource(id = R.drawable.background), // Replace with actual drawable
            contentDescription = "Background Pattern",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun IndicatorDots() {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
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
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color = Color.Gray, shape = CircleShape)
        )
    }
}
@SuppressLint("RememberReturnType")
@Composable
fun NextButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(62.dp)
            .background(color = Color(0xB0252525), shape = CircleShape)
            .border(1.dp, Color.White, shape = CircleShape)
            .clickable(
                onClick = onClick,
                indication = null, // No visual feedback
                interactionSource = remember { MutableInteractionSource() } // Required for no indication
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right_foreground), // Replace with actual drawable
            contentDescription = "Next",
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
    }
}



@Composable
fun GreenText() {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color(0xFF7DFFA8))) {
                append("Eat ")
            }
            withStyle(style = SpanStyle(color = Color.White)) {
                append("Well,\n")
            }
            withStyle(style = SpanStyle(color = Color(0xFF7DFFA8))) {
                append("Feel ")
            }
            withStyle(style = SpanStyle(color = Color.White)) {
                append("Well,\n")
            }
            withStyle(style = SpanStyle(color = Color(0xFF7DFFA8))) {
                append("Live ")
            }
            withStyle(style = SpanStyle(color = Color.White)) {
                append("Well.")
            }
        },
        fontSize = 54.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Left,
        lineHeight = 75.sp
    )
}

