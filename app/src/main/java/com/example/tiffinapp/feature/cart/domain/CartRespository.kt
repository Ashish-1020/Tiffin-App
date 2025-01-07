package com.example.tiffin.feature.cart.domain
import com.example.tiffin.core.data.UserProfile
import com.example.tiffin.feature.cart.data.Cart
import com.example.tiffin.feature.cart.data.CartItem
import com.example.tiffin.feature.home.data.Meal
import com.example.tiffinapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) {

    suspend fun fetchCartItems(): List<CartItem> {
        val userId = firebaseAuth.currentUser?.uid ?: throw Exception("User not logged in.")

        val cartSnapshot = firestore.collection("carts")
            .document(userId)
            .get()
            .await()

        return cartSnapshot.toObject(Cart::class.java)?.items ?: emptyList()
    }

    suspend fun fetchMeals(): List<Meal> {
        // Mock meal data
        return listOf(
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
            ),
            Meal(
                id = "20",
                name = "Chicken Alfredo",
                imageUrl = R.drawable.ic_sample_item,
                calories = 600,
                price = 350.0, // ₹
                mealtype = "Non-Veg",
                category = "Dinner",
                day = "Saturday",
                dietarylabel = "High Protein",
                offer = "",
                description = "Creamy Alfredo with grilled chicken."
            )
        )
    }


    suspend fun getMealDetails():List<Meal> {
        return  fetchMeals()
    }

    suspend fun getCartDetails(): List<CartItem> {
        val cartItems = fetchCartItems()
        val allMeals = fetchMeals()

        return cartItems.mapNotNull { cartItem ->
            val meal = allMeals.find { it.id == cartItem.mealId }
            if (meal != null) {
                CartItem(
                    mealId = meal.id,
                    quantity = cartItem.quantity,
                    userId = "",
                )
            } else {
                null
            }
        }
    }

    suspend fun deleteCartItem(mealId: String) {
        val userId = firebaseAuth.currentUser?.uid ?: throw Exception("User not logged in.")

        val cartDocumentRef = firestore.collection("carts").document(userId)
        firestore.runTransaction { transaction ->
            val snapshot = transaction.get(cartDocumentRef)
            val cart = snapshot.toObject(Cart::class.java) ?: throw Exception("Cart not found.")

            // Filter out the CartItem with the matching mealId
            val updatedItems = cart.items.filterNot { it.mealId == mealId }.toMutableList()

            // Update the cart in Firestore
            transaction.update(cartDocumentRef, "items", updatedItems)
        }.await()
    }


   suspend fun fetchUserProfileFromFirestore(): UserProfile {
        val userId = firebaseAuth.currentUser?.uid ?: throw Exception("User not logged in.")
        return try {
            val userDocument = firestore.collection("users").document(userId).get().await()
            userDocument.toObject(UserProfile::class.java)
                ?: throw Exception("User profile not found in Firestore.")
        } catch (e: FirebaseFirestoreException) {
            // Handle Firestore exception
            throw Exception("Failed to retrieve user profile from Firestore: ${e.message}")
        }
    }

}

