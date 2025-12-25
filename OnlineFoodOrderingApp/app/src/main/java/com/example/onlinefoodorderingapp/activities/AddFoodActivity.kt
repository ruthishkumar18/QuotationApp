package com.example.onlinefoodorderingapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinefoodorderingapp.database.DBHelper
import com.example.onlinefoodorderingapp.databinding.ActivityAddFoodBinding

class AddFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddFoodBinding
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val price = binding.etPrice.text.toString()

            if (name.isNotEmpty() && price.isNotEmpty()) {
                dbHelper.addFood(name, price.toDouble())
                Toast.makeText(this, "Food Added", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
