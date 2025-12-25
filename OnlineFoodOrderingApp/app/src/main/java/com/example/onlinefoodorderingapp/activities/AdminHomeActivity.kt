package com.example.onlinefoodorderingapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinefoodorderingapp.databinding.ActivityAdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddFood.setOnClickListener {
            startActivity(Intent(this, AddFoodActivity::class.java))
        }

        binding.btnScanOrder.setOnClickListener {
            startActivity(Intent(this, ScanOrderActivity::class.java))
        }
    }
}
