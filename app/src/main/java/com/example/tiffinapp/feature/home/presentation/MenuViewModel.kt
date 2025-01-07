package com.example.tiffinapp.feature.home.presentation
import androidx.lifecycle.ViewModel
import com.example.tiffin.feature.home.data.Meal
import com.example.tiffinapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
/*
@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel() {

    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals: StateFlow<List<Meal>> get() = _meals

    private val _totalCost = MutableStateFlow<Double>(0.0)
    val totalCost: StateFlow<Double> get() = _totalCost

    init {
        loadMeals()
    }

    private fun loadMeals() {
        val mealList =listOf(
            Meal(
                id = "1",
                name = "Veg Burger",
                imageUrl = R.drawable.ic_green_salad,
                calories = 350,
                price = 150.0, // ₹
                mealtype = "Veg",
                category = "Lunch",
                day = "Monday",
                dietarylabel = "Vegetarian",
                offer = "10% Off",
                description = "Delicious veggie burger with fresh lettuce."
            ),
            Meal(
                id = "2",
                name = "Chicken Burger",
                imageUrl = R.drawable.ic_sample_item,
                calories = 450,
                price = 220.0, // ₹
                mealtype = "Non-Veg",
                category = "Lunch",
                day = "Tuesday",
                dietarylabel = "High Protein",
                offer = "25% Off 🎉 🌟",
                description = "Juicy chicken burger with special sauce."
            ),
            Meal(
                id = "3",
                name = "Vegetable Salad",
                imageUrl = R.drawable.ic_green_salad,
                calories = 150,
                price = 120.0, // ₹
                mealtype = "Veg",
                category = "Breakfast",
                day = "Wednesday",
                dietarylabel = "Low Calorie",
                offer = "Free Drink",
                description = "A healthy salad with mixed vegetables."
            ),
            Meal(
                id = "4",
                name = "Grilled Chicken",
                imageUrl = R.drawable.ic_green_salad,
                calories = 500,
                price = 350.0, // ₹
                mealtype = "Non-Veg",
                category = "Dinner",
                day = "Thursday",
                dietarylabel = "Keto Friendly",
                offer = "Buy 1 Get 1 Free",
                description = "Tender grilled chicken with seasoning."
            ),
            Meal(
                id = "5",
                name = "Pasta Alfredo",
                imageUrl = R.drawable.ic_green_salad,
                calories = 600,
                price = 250.0, // ₹
                mealtype = "Veg",
                category = "Dinner",
                day = "Friday",
                dietarylabel = "Vegetarian",
                offer = "15% Off",
                description = "Creamy Alfredo pasta with fresh herbs."
            ),
            Meal(
                id = "6",
                name = "Steak",
                imageUrl = R.drawable.ic_sample_item,
                calories = 700,
                price = 600.0, // ₹
                mealtype = "Non-Veg",
                category = "Dinner",
                day = "Saturday",
                dietarylabel = "High Protein",
                offer = "20% Off",
                description = "Juicy steak with mashed potatoes."
            ),
            Meal(
                id = "7",
                name = "Oatmeal Bowl",
                imageUrl = R.drawable.ic_green_salad,
                calories = 200,
                price = 90.0, // ₹
                mealtype = "Veg",
                category = "Breakfast",
                day = "Sunday",
                dietarylabel = "Low Calorie",
                offer = "5% Off",
                description = "Healthy oatmeal with fruits and nuts."
            ),
            Meal(
                id = "8",
                name = "Fish Tacos",
                imageUrl = R.drawable.ic_sample_item,
                calories = 450,
                price = 180.0, // ₹
                mealtype = "Non-Veg",
                category = "Lunch",
                day = "Monday",
                dietarylabel = "High Protein",
                offer = "Special Combo",
                description = "Crispy fish tacos with tangy salsa."
            ),
            Meal(
                id = "9",
                name = "Veggie Wrap",
                imageUrl = R.drawable.ic_green_salad,
                calories = 300,
                price = 140.0, // ₹
                mealtype = "Veg",
                category = "Lunch",
                day = "Tuesday",
                dietarylabel = "Vegetarian",
                offer = "10% Off",
                description = "Fresh veggie wrap with hummus."
            ),
            Meal(
                id = "10",
                name = "Chicken Wings",
                imageUrl = R.drawable.ic_sample_item,
                calories = 650,
                price = 280.0, // ₹
                mealtype = "Non-Veg",
                category = "Dinner",
                day = "Wednesday",
                dietarylabel = "High Protein",
                offer = "20% Off",
                description = "Spicy chicken wings with dip."
            ),
            Meal(
                id = "11",
                name = "Fruit Salad",
                imageUrl = R.drawable.ic_green_salad,
                calories = 120,
                price = 100.0, // ₹
                mealtype = "Veg",
                category = "Breakfast",
                day = "Thursday",
                dietarylabel = "Low Calorie",
                offer = "Buy 2 Get 1 Free",
                description = "Fresh seasonal fruits in a bowl."
            ),
            Meal(
                id = "12",
                name = "Beef Burger",
                imageUrl = R.drawable.ic_sample_item,
                calories = 600,
                price = 250.0, // ₹
                mealtype = "Non-Veg",
                category = "Lunch",
                day = "Friday",
                dietarylabel = "High Protein",
                offer = "Combo Meal",
                description = "Juicy beef burger with crispy fries."
            ),
            Meal(
                id = "13",
                name = "Veggie Pizza",
                imageUrl = R.drawable.ic_green_salad,
                calories = 500,
                price = 400.0, // ₹
                mealtype = "Veg",
                category = "Dinner",
                day = "Saturday",
                dietarylabel = "Vegetarian",
                offer = "15% Off",
                description = "Cheesy veggie pizza with fresh toppings."
            ),
            Meal(
                id = "14",
                name = "Chicken Salad",
                imageUrl = R.drawable.ic_sample_item,
                calories = 350,
                price = 200.0, // ₹
                mealtype = "Non-Veg",
                category = "Lunch",
                day = "Sunday",
                dietarylabel = "High Protein",
                offer = "10% Off",
                description = "Fresh salad with grilled chicken."
            ),
            Meal(
                id = "15",
                name = "Pancakes",
                imageUrl = R.drawable.ic_green_salad,
                calories = 400,
                price = 120.0, // ₹
                mealtype = "Veg",
                category = "Breakfast",
                day = "Monday",
                dietarylabel = "Vegetarian",
                offer = "Free Coffee",
                description = "Fluffy pancakes with syrup."
            ),
            Meal(
                id = "16",
                name = "BBQ Ribs",
                imageUrl = R.drawable.ic_sample_item,
                calories = 800,
                price = 500.0, // ₹
                mealtype = "Non-Veg",
                category = "Dinner",
                day = "Tuesday",
                dietarylabel = "High Protein",
                offer = "20% Off",
                description = "Smoky BBQ ribs with coleslaw."
            ),
            Meal(
                id = "17",
                name = "Smoothie Bowl",
                imageUrl = R.drawable.ic_green_salad,
                calories = 250,
                price = 150.0, // ₹
                mealtype = "Veg",
                category = "Breakfast",
                day = "Wednesday",
                dietarylabel = "Low Calorie",
                offer = "5% Off",
                description = "Smoothie bowl with granola and fruits."
            ),
            Meal(
                id = "18",
                name = "Grilled Salmon",
                imageUrl = R.drawable.ic_sample_item,
                calories = 500,
                price = 450.0, // ₹
                mealtype = "Non-Veg",
                category = "Dinner",
                day = "Thursday",
                dietarylabel = "Omega-3 Rich",
                offer = "Combo Meal",
                description = "Grilled salmon with steamed veggies."
            ),
            Meal(
                id = "19",
                name = "Veggie Pasta",
                imageUrl = R.drawable.ic_green_salad,
                calories = 450,
                price = 200.0, // ₹
                mealtype = "Veg",
                category = "Lunch",
                day = "Friday",
                dietarylabel = "Vegetarian",
                offer = "Buy 1 Get 1",
                description = "Delicious veggie pasta in tomato sauce."
            )

        )
        _meals.value = mealList
        updateTotalCost(mealList,isVegOnly = true)
    }

        fun updateTotalCost(meals: List<Meal>, isVegOnly: Boolean) {
            val filteredMeals = if (isVegOnly) {
                meals.filter { it.mealtype == "Veg" }
            } else {
                meals
            }
            _totalCost.value = filteredMeals.sumOf { it.price }
        }


}*/


@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel() {

    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals: StateFlow<List<Meal>> get() = _meals

    private val _totalCost = MutableStateFlow<Double>(0.0)
    val totalCost: StateFlow<Double> get() = _totalCost

    // State to track whether Veg mode is enabled
    private val _isVegMode = MutableStateFlow(false)
    val isVegMode: StateFlow<Boolean> get() = _isVegMode

    init {
        loadMeals()
    }

    private fun loadMeals() {
        val mealList =listOf(
            Meal(
                id = "1",
                name = "Veg Burger",
                imageUrl = R.drawable.ic_green_salad,
                calories = 350,
                price = 150.0, // ₹
                mealtype = "Veg",
                category = "Lunch",
                day = "Monday",
                dietarylabel = "Vegetarian",
                offer = "10% Off",
                description = "Delicious veggie burger with fresh lettuce."
            ),
            Meal(
                id = "2",
                name = "Chicken Burger",
                imageUrl = R.drawable.ic_sample_item,
                calories = 450,
                price = 220.0, // ₹
                mealtype = "Non-Veg",
                category = "Lunch",
                day = "Tuesday",
                dietarylabel = "High Protein",
                offer = "25% Off 🎉 🌟",
                description = "Juicy chicken burger with special sauce."
            ),
            Meal(
                id = "3",
                name = "Vegetable Salad",
                imageUrl = R.drawable.ic_green_salad,
                calories = 150,
                price = 120.0, // ₹
                mealtype = "Veg",
                category = "Breakfast",
                day = "Wednesday",
                dietarylabel = "Low Calorie",
                offer = "Free Drink",
                description = "A healthy salad with mixed vegetables."
            ),
            Meal(
                id = "4",
                name = "Grilled Chicken",
                imageUrl = R.drawable.ic_green_salad,
                calories = 500,
                price = 350.0, // ₹
                mealtype = "Non-Veg",
                category = "Dinner",
                day = "Thursday",
                dietarylabel = "Keto Friendly",
                offer = "Buy 1 Get 1 Free",
                description = "Tender grilled chicken with seasoning."
            ),
            Meal(
                id = "5",
                name = "Pasta Alfredo",
                imageUrl = R.drawable.ic_green_salad,
                calories = 600,
                price = 250.0, // ₹
                mealtype = "Veg",
                category = "Dinner",
                day = "Friday",
                dietarylabel = "Vegetarian",
                offer = "15% Off",
                description = "Creamy Alfredo pasta with fresh herbs."
            ),
            Meal(
                id = "6",
                name = "Steak",
                imageUrl = R.drawable.ic_sample_item,
                calories = 700,
                price = 600.0, // ₹
                mealtype = "Non-Veg",
                category = "Dinner",
                day = "Saturday",
                dietarylabel = "High Protein",
                offer = "20% Off",
                description = "Juicy steak with mashed potatoes."
            ),
            Meal(
                id = "7",
                name = "Oatmeal Bowl",
                imageUrl = R.drawable.ic_green_salad,
                calories = 200,
                price = 90.0, // ₹
                mealtype = "Veg",
                category = "Breakfast",
                day = "Sunday",
                dietarylabel = "Low Calorie",
                offer = "5% Off",
                description = "Healthy oatmeal with fruits and nuts."
            ),
            Meal(
                id = "8",
                name = "Fish Tacos",
                imageUrl = R.drawable.ic_sample_item,
                calories = 450,
                price = 180.0, // ₹
                mealtype = "Non-Veg",
                category = "Lunch",
                day = "Monday",
                dietarylabel = "High Protein",
                offer = "Special Combo",
                description = "Crispy fish tacos with tangy salsa."
            ),
            Meal(
                id = "9",
                name = "Veggie Wrap",
                imageUrl = R.drawable.ic_green_salad,
                calories = 300,
                price = 140.0, // ₹
                mealtype = "Veg",
                category = "Lunch",
                day = "Tuesday",
                dietarylabel = "Vegetarian",
                offer = "10% Off",
                description = "Fresh veggie wrap with hummus."
            ),
            Meal(
                id = "10",
                name = "Chicken Wings",
                imageUrl = R.drawable.ic_sample_item,
                calories = 650,
                price = 280.0, // ₹
                mealtype = "Non-Veg",
                category = "Dinner",
                day = "Wednesday",
                dietarylabel = "High Protein",
                offer = "20% Off",
                description = "Spicy chicken wings with dip."
            ),
            Meal(
                id = "11",
                name = "Fruit Salad",
                imageUrl = R.drawable.ic_green_salad,
                calories = 120,
                price = 100.0, // ₹
                mealtype = "Veg",
                category = "Breakfast",
                day = "Thursday",
                dietarylabel = "Low Calorie",
                offer = "Buy 2 Get 1 Free",
                description = "Fresh seasonal fruits in a bowl."
            ),
            Meal(
                id = "12",
                name = "Beef Burger",
                imageUrl = R.drawable.ic_sample_item,
                calories = 600,
                price = 250.0, // ₹
                mealtype = "Non-Veg",
                category = "Lunch",
                day = "Friday",
                dietarylabel = "High Protein",
                offer = "Combo Meal",
                description = "Juicy beef burger with crispy fries."
            ),
            Meal(
                id = "13",
                name = "Veggie Pizza",
                imageUrl = R.drawable.ic_green_salad,
                calories = 500,
                price = 400.0, // ₹
                mealtype = "Veg",
                category = "Dinner",
                day = "Saturday",
                dietarylabel = "Vegetarian",
                offer = "15% Off",
                description = "Cheesy veggie pizza with fresh toppings."
            ),
            Meal(
                id = "14",
                name = "Chicken Salad",
                imageUrl = R.drawable.ic_sample_item,
                calories = 350,
                price = 200.0, // ₹
                mealtype = "Non-Veg",
                category = "Lunch",
                day = "Sunday",
                dietarylabel = "High Protein",
                offer = "10% Off",
                description = "Fresh salad with grilled chicken."
            ),
            Meal(
                id = "15",
                name = "Pancakes",
                imageUrl = R.drawable.ic_green_salad,
                calories = 400,
                price = 120.0, // ₹
                mealtype = "Veg",
                category = "Breakfast",
                day = "Monday",
                dietarylabel = "Vegetarian",
                offer = "Free Coffee",
                description = "Fluffy pancakes with syrup."
            ),
            Meal(
                id = "16",
                name = "BBQ Ribs",
                imageUrl = R.drawable.ic_sample_item,
                calories = 800,
                price = 500.0, // ₹
                mealtype = "Non-Veg",
                category = "Dinner",
                day = "Tuesday",
                dietarylabel = "High Protein",
                offer = "20% Off",
                description = "Smoky BBQ ribs with coleslaw."
            ),
            Meal(
                id = "17",
                name = "Smoothie Bowl",
                imageUrl = R.drawable.ic_green_salad,
                calories = 250,
                price = 150.0, // ₹
                mealtype = "Veg",
                category = "Breakfast",
                day = "Wednesday",
                dietarylabel = "Low Calorie",
                offer = "5% Off",
                description = "Smoothie bowl with granola and fruits."
            ),
            Meal(
                id = "18",
                name = "Grilled Salmon",
                imageUrl = R.drawable.ic_sample_item,
                calories = 500,
                price = 450.0, // ₹
                mealtype = "Non-Veg",
                category = "Dinner",
                day = "Thursday",
                dietarylabel = "Omega-3 Rich",
                offer = "Combo Meal",
                description = "Grilled salmon with steamed veggies."
            ),
            Meal(
                id = "19",
                name = "Veggie Pasta",
                imageUrl = R.drawable.ic_green_salad,
                calories = 450,
                price = 200.0, // ₹
                mealtype = "Veg",
                category = "Lunch",
                day = "Friday",
                dietarylabel = "Vegetarian",
                offer = "Buy 1 Get 1",
                description = "Delicious veggie pasta in tomato sauce."
            )

        )
        _meals.value = mealList
        updateTotalCost()
    }

    // Function to update total cost based on mode (Veg or Non-Veg)
    private fun updateTotalCost() {
        val filteredMeals = if (_isVegMode.value) {
            _meals.value.filter { it.mealtype == "Veg" }
        } else {
            _meals.value  // No filter, show both Veg and Non-Veg meals
        }
        _totalCost.value = filteredMeals.sumOf { it.price }
    }

    // Function to toggle between Veg and Non-Veg mode
    fun toggleMode() {
        _isVegMode.value = !_isVegMode.value
        updateTotalCost()  // Update total cost when the mode changes
    }
}
