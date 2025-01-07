package com.example.tiffinapp.home.UI

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiffin.feature.home.data.Meal
import com.example.tiffin.feature.home.data.MenuItem
import com.example.tiffinapp.R
import com.example.tiffinapp.feature.home.domain.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MenuRepository
) : ViewModel() {

    // State for menu items
    private val _menuItems = MutableStateFlow<List<Meal>>(emptyList())
    val menuItems: StateFlow<List<Meal>> = _menuItems.asStateFlow()

    // State for Veg mode toggle
    var isVegMode = mutableStateOf(false)
        private set

    // State for search query
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        fetchMenuItems()
    }

    // Update search query and fetch menu items
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        fetchMenuItems()
    }

    // Toggle Veg mode and fetch menu items
    fun toggleVegMode() {
        isVegMode.value = !isVegMode.value
        fetchMenuItems()
    }

    // Fetch menu items from the repository with filters
    private fun fetchMenuItems() {
        viewModelScope.launch {
            try {
                var items = repository.getMealItems()

                // Filter by Veg mode
                if (isVegMode.value) {
                    items = items.filter { it.mealtype == "Veg"}
                }

                // Filter by search query
                if (_searchQuery.value.isNotEmpty()) {
                    val query = _searchQuery.value.lowercase()
                    items = items.filter {
                        it.name.lowercase().contains(query) ||
                                it.description.lowercase().contains(query)
                    }
                }

                _menuItems.value = items
            } catch (e: Exception) {
                // Handle error, log or show a toast
            }
        }
    }


    fun addtocart(mealId:String){
        repository.addtocart(mealId)
    }

}
