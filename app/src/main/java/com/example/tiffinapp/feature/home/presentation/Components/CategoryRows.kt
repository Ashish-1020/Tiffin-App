package com.example.tiffinapp.feature.home.presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiffinapp.R


@Composable
fun CategoriesRow(onCategoryClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp, bottom = 16.dp, start = 24.dp, end = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CategoryItem(
            iconResId = R.drawable.ic_promo1,
            title = "Big\nPromo",
            onClick = { onCategoryClick("Big Promo") }
        )
        CategoryItem(
            iconResId = R.drawable.ic_promo2,
            title = "Your\nPreferences",
            onClick = { onCategoryClick("Your Preferences") }
        )
        CategoryItem(
            iconResId = R.drawable.ic_promo3,
            title = "Today's\nMenu",
            onClick = { onCategoryClick("Today's Menu") }
        )
        CategoryItem(
            iconResId = R.drawable.ic_promo4,
            title = "Weekly\nMenu",
            onClick = { onCategoryClick("Weekly Menu") }
        )
    }
}

@Composable
fun CategoryItem(iconResId: Int, title: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = title,
            modifier = Modifier.size(55.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}




