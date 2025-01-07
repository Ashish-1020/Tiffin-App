package com.example.tiffin.feature.wallet.data

import com.google.firebase.Timestamp

// feature/wallet/data/model/Wallet.kt
data class Wallet(
    val userId: String = "",
    val balance: Double = 0.0,
    val transactions: List<Transaction> = emptyList()
)

data class Transaction(
    val transactionId: String = "",
    val transactionAmount: Double = 0.0,
    val transactionTimestamp: Timestamp = Timestamp.now(),
    val transactionType: String = "", // "credit" or "debit"
    val transactionDetail: String = ""
)

