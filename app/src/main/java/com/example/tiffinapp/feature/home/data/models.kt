package com.example.tiffin.feature.home.data

// feature/home/data/model/Meal.kt
data class Meal(
    val id: String="",
    val name: String="",
    val imageUrl: Int=0,
    val calories: Int=0,
    val price: Double=0.0,
    val mealtype:String="",//Veg or Non-veg
    val category: String="", // Breakfast, Lunch, Dinner
    val day:String="",  //weekday monday ,tuesday,wednesday,thursday,friday,saturday,sunday
    val dietarylabel:String="",
    val offer:String="",
    val description: String=""
)

data class MenuItem(
    val mealType: String,
    val title: String,
    val description: String,
    val calories: Int,
    val imageResId: Int
)
