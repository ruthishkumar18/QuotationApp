package com.example.onlinefoodorderingapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinefoodorderingapp.database.DBHelper
import com.example.onlinefoodorderingapp.databinding.ActivityOrderConfirmationBinding
import com.example.onlinefoodorderingapp.utils.QRGenerator
import java.util.UUID

class OrderConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderConfirmationBinding
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        val orderId = UUID.randomUUID().toString()
        binding.qrOrder.setImageBitmap(QRGenerator.generate(orderId))

        dbHelper.saveOrder(orderId)
        dbHelper.clearCart()
    }
}
