package com.example.tiffin.feature.cart.data

import com.example.tiffin.feature.home.data.Meal

// feature/cart/data/model/CartItem.kt
data class CartItem(
    val userId:String="",
    val mealId: String="",
    var quantity: Int=1
)

data class Cart(
    val userId: String = "",
    val items: MutableList<CartItem> = mutableListOf()
)



