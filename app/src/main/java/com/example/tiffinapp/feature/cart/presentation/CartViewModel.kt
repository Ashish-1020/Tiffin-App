package com.example.tiffin.feature.cart.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiffin.core.data.UserProfile
import com.example.tiffin.feature.cart.data.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import com.example.tiffin.feature.cart.domain.CartRepository
import com.example.tiffin.feature.home.data.Meal

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import javax.inject.Inject
@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> get() = _cartItems

    private val _mealDetails = MutableStateFlow<List<Meal>>(emptyList())
    val mealDetails: StateFlow<List<Meal>> get() = _mealDetails
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> get() = _userProfile
    init {
        fetchCartData()
        fetchMealDetails()
        fetchUserProfile()
    }

    private fun fetchCartData() {
        viewModelScope.launch {
            try {
                val items = repository.getCartDetails()
                _cartItems.value = items
            } catch (e: Exception) {
                println("Error fetching cart data: ${e.message}")
            }
        }
    }

    private fun fetchMealDetails() {
        viewModelScope.launch {
            try {
                val meals = repository.getMealDetails()
                _mealDetails.value = meals
            } catch (e: Exception) {
                println("Error fetching meal details: ${e.message}")
            }
        }
    }
    private fun fetchUserProfile() {
        viewModelScope.launch {
            try {
                _userProfile.value = repository.fetchUserProfileFromFirestore()
            } catch (e: Exception) {
                println("Error fetching user profile: ${e.message}")
            }
        }
    }

    fun incrementQuantity(index: Int) {
        val updatedList = _cartItems.value.toMutableList()
        updatedList[index] = updatedList[index].copy(quantity = updatedList[index].quantity + 1)
        _cartItems.value = updatedList
    }

    fun decrementQuantity(index: Int) {
        val updatedList = _cartItems.value.toMutableList()
        val cartItem = updatedList[index]

        if (cartItem.quantity > 1) {
            // Decrease the quantity
            updatedList[index] = cartItem.copy(quantity = cartItem.quantity - 1)
            _cartItems.value = updatedList
        } else {
            // Remove item from Firestore
            viewModelScope.launch {
                try {
                    repository.deleteCartItem(cartItem.mealId)
                    updatedList.removeAt(index)
                    _cartItems.value = updatedList
                } catch (e: Exception) {
                    println("Error deleting cart item: ${e.message}")
                }
            }
        }
    }


}
