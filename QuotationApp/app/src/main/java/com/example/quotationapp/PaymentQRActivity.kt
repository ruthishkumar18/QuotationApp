package com.example.quotationapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentQRActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_qr)

        val qrUri = intent.getStringExtra("qrUri")

        if (qrUri.isNullOrEmpty()) {
            Toast.makeText(this, "Invalid payment link", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val btnOpenUPI = findViewById<Button>(R.id.btnOpenUPI)

        btnOpenUPI.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(qrUri))
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "No UPI app found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
