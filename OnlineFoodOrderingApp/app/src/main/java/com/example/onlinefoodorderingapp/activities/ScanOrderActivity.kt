package com.example.onlinefoodorderingapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinefoodorderingapp.database.DBHelper
import com.example.onlinefoodorderingapp.databinding.ActivityScanOrderBinding
import com.google.zxing.integration.android.IntentIntegrator

class ScanOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanOrderBinding
    private lateinit var dbHelper: DBHelper

    private val qrScannerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val result = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        if (result.contents != null) {
            if (dbHelper.verifyOrder(result.contents)) {
                Toast.makeText(this, "Order Accepted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Order already verified", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Scan failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        binding.btnScan.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setBeepEnabled(true)
            qrScannerLauncher.launch(integrator.createScanIntent())
        }
    }
}
