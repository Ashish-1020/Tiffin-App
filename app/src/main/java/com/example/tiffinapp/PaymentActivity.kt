package com.example.tiffinapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class PaymentActivity : AppCompatActivity(), PaymentResultListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the amount from intent
        val amount = intent.getStringExtra("amount") ?: "0"

        // Start payment process directly
        startPayment(amount)

        // Initialize Razorpay Checkout
        Checkout.preload(applicationContext)

        // Set the background color of the activity using Jetpack Compose
        setContent {
            PaymentScreenBackground()
        }
    }

    private fun startPayment(amount: String) {
        val checkout = Checkout()
        checkout.setKeyID(BuildConfig.API_KEY)

        try {
            val paymentDetails = JSONObject()
            paymentDetails.put("name", "Tiffin App")
            paymentDetails.put("description", "Adding money to wallet")
            paymentDetails.put("image", "https://your-logo-url.com/logo.png")
            paymentDetails.put("currency", "INR")
            paymentDetails.put("amount", amount.toDouble() * 100) // Razorpay accepts amount in paise

            val prefill = JSONObject()
            prefill.put("email", "user@example.com")
            prefill.put("contact", "1234567890")

            paymentDetails.put("prefill", prefill)

            checkout.open(this, paymentDetails)
        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPaymentSuccess(razorpayPaymentID: String?) {

        Toast.makeText(this, "Payment successful: $razorpayPaymentID", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onPaymentError(code: Int, response: String?) {

        Toast.makeText(this, "Payment failed: $response", Toast.LENGTH_SHORT).show()
    }

    @Composable
    fun PaymentScreenBackground() {
        Surface(
            modifier = androidx.compose.ui.Modifier.background(Color(0xFF93D8A2)),
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}
