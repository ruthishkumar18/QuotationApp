package com.example.onlinefoodorderingapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinefoodorderingapp.adapters.CartAdapter
import com.example.onlinefoodorderingapp.database.DBHelper
import com.example.onlinefoodorderingapp.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CartAdapter(dbHelper.getCartItems(), this)

        binding.btnPayment.setOnClickListener {
            startActivity(Intent(this, PaymentActivity::class.java))
        }
    }
}
