package com.example.tiffinapp.feature.home.presentation.Components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiffin.feature.home.data.Meal
import com.example.tiffinapp.R
import com.example.tiffinapp.home.UI.HomeViewModel


@Composable
fun MenuList(context: Context,menuItems: List<Meal>,viewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        menuItems.forEach { menuItem ->
            MenuItemCard(context,menuItem,viewModel)
        }
    }
}

@Composable
fun MenuItemCard(context: Context,menuItem: Meal,viewModel: HomeViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 4.dp),
            onClick = {
                // click
            },
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            border = BorderStroke(0.5.dp, Color(0xFF93D8A2)),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // First Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left Column
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {
                        Text(
                            text = menuItem.name,
                            style = MaterialTheme.typography.labelMedium.copy(fontSize = 15.sp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "$ 564",
                                style = MaterialTheme.typography.labelMedium.copy(fontSize = 16.sp),
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Divider(
                                color = Color.Gray,
                                modifier = Modifier
                                    .height(20.dp)
                                    .width(6.dp)
                                    .padding(horizontal = 2.dp)
                            )
                            Image(
                                painter = painterResource(R.drawable.ic_calories),
                                contentDescription = "Calories",
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "${menuItem.calories} kcal",
                                style = MaterialTheme.typography.labelMedium.copy(fontSize = 16.sp),
                                color = Color.Black
                            )
                        }
                    }
                    // Right Column
                    Image(
                        painter = if (menuItem.mealtype == "Veg") painterResource(R.drawable.veg) else painterResource(
                            R.drawable.non_veg
                        ),
                        contentDescription = "Meal Type",
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Second Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left Column
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {
                        Text(
                            text = menuItem.dietarylabel,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = menuItem.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "view details->",
                            color =  colorResource(id = R.color.green),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.clickable {
                                //expand it
                            })



                    }
                    // Right Column
                    Image(
                        painter = painterResource(id = menuItem.imageUrl),
                        contentDescription = menuItem.name,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )


                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Last Row
                    Button(
                        onClick = {

                         //add to cartitem

                            Toast.makeText(context, "Added ${menuItem.name} to cart", Toast.LENGTH_SHORT).show()
                            viewModel.addtocart(menuItem.id)



                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green)),
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(top = 4.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.cart_icon),
                            contentDescription = "Cart Icon"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Add to cart")
                    }



                }
            }
        }
    }
}







