package com.example.tiffin.core.data

import com.example.tiffin.feature.home.data.Meal
import com.example.tiffinapp.R

data class UserProfile(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val dateOfBirth: String? = null,
    val addresses: List<Address> = emptyList(),
    val profilePictureUrl: String? = null
)

data class Address(
    val type: String = "",
    val address: String = ""
)



