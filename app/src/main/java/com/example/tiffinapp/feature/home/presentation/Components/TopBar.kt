package com.example.tiffinapp.feature.home.presentation.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiffinapp.R
import com.example.tiffinapp.home.UI.HomeViewModel

@Composable
fun TopBar(viewModel: HomeViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Current Location",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            )
            Card(
                modifier = Modifier
                    .padding(top = 6.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF93D8A2)
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "Location",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "JL. Kampung Melon No. 32",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
        Row {
            Box(modifier = Modifier.padding(top = 4.dp)) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notification",
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            VegModeToggle(viewModel = viewModel)
        }
    }
}

@Composable
fun VegModeToggle(viewModel: HomeViewModel) {
    val isVeg by viewModel.isVegMode

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = if (isVeg) "VEG" else "NON-VEG",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                color = if (isVeg) Color(0xFF93D8A2) else Color.Gray
            ),
            letterSpacing = 1.sp
        )
        Text(
            text = "MODE",
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 12.sp,
                color = if (isVeg) Color(0xFF93D8A2) else Color.Gray
            )
        )
        Switch(
            checked = isVeg,
            onCheckedChange = { viewModel.toggleVegMode() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF93D8A2),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.Gray
            ),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
