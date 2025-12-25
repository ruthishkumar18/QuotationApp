package com.example.onlinefoodorderingapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinefoodorderingapp.databinding.ActivityPaymentBinding
import com.example.onlinefoodorderingapp.utils.QRGenerator

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.qrImage.setImageBitmap(QRGenerator.generate("PAYMENT_SUCCESS"))

        binding.btnConfirm.setOnClickListener {
            startActivity(Intent(this, OrderConfirmationActivity::class.java))
            finish()
        }
    }
}
