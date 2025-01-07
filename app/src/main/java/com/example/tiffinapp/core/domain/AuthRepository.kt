package com.example.tiffin.core.domain

import com.example.tiffin.core.data.UserProfile
import com.example.tiffin.feature.cart.data.CartItem
import com.example.tiffin.feature.wallet.data.Wallet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/*
// core/domain/repository/AuthRepository.kt
interface AuthRepository {
    suspend fun signIn(email: String, password: String): String // Returns userId
    suspend fun signUp(email: String, password: String, userProfile: UserProfile): String
    suspend fun signOut(): Boolean
    fun getCurrentUserId(): String?
}
*/

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

import com.google.firebase.firestore.FirebaseFirestoreException

class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    // Register User
    suspend fun registerUser(
        name: String,
        email: String,
        password: String,
        phoneNumber: String,
        dateOfBirth: String?
    ): Result<UserProfile> {
        return try {
            // Create user with email and password
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user ?: throw Exception("Failed to create user.")

            // Build the user profile
            val userProfile = UserProfile(
                id = firebaseUser.uid,
                name = name,
                email = email,
                phoneNumber = phoneNumber,
                dateOfBirth = dateOfBirth,
                addresses = emptyList(), // Default empty list
                profilePictureUrl = null // Default null
            )

            // Save the user profile in Firestore
            saveUserProfileToFirestore(firebaseUser, userProfile)
            // Initialize wallet for the user
            initializeUserWallet(firebaseUser)
            initializeUserCart(firebaseUser)

            Result.success(userProfile)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Save User Profile to Firestore
    private suspend fun saveUserProfileToFirestore(user: FirebaseUser, userProfile: UserProfile) {
        val userDocument = firestore.collection("users").document(user.uid)
        userDocument.set(userProfile).await()
    }

    // Login User
    suspend fun loginUser(email: String, password: String): Result<UserProfile> {
        return try {
            // Authenticate the user with Firebase Authentication
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val currentUser = firebaseAuth.currentUser

            // Check if the user is logged in
            if (currentUser != null) {
                // Fetch the user profile from Firestore
                val userProfile = fetchUserProfileFromFirestore(currentUser.uid)
                Result.success(userProfile)
            } else {
                Result.failure(Exception("User not found."))
            }
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            // Specific Firebase Authentication error handling
            Result.failure(Exception("Invalid email or password. Please try again."))
        } catch (e: FirebaseFirestoreException) {
            // Specific Firestore error handling
            Result.failure(Exception("Failed to retrieve data from Firestore."))
        } catch (e: Exception) {
            // Generic error handling
            Result.failure(e)
        }
    }

    // Fetch User Profile from Firestore
    private suspend fun fetchUserProfileFromFirestore(userId: String): UserProfile {
        return try {
            val userDocument = firestore.collection("users").document(userId).get().await()
            userDocument.toObject(UserProfile::class.java)
                ?: throw Exception("User profile not found in Firestore.")
        } catch (e: FirebaseFirestoreException) {
            // Handle Firestore exception
            throw Exception("Failed to retrieve user profile from Firestore: ${e.message}")
        }
    }

    // Check if User is Logged In
    fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    // Logout User
    fun logout() {
        firebaseAuth.signOut()
    }




    /* ******************************************Wallet Creation************************/
    // Initialize Wallet for the User
    private suspend fun initializeUserWallet(user: FirebaseUser) {
        val wallet = Wallet(
            userId = user.uid,
            balance = 0.0, // Initial balance set to 0
            transactions = emptyList() // No transactions initially
        )

        // Save the wallet in Firestore under a `wallet` collection
        firestore.collection("wallets").document(user.uid).set(wallet).await()
    }

    /**************************************Cart Creation**********************************/

// Initialize Cart for the User
    private suspend fun initializeUserCart(user: FirebaseUser) {
        val cart = hashMapOf(
            "userId" to user.uid,
            "items" to emptyList<CartItem>() // Initialize with an empty cart
        )

        firestore.collection("carts").document(user.uid).set(cart).await()
    }





}
