package com.example.voicequoteai.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.voicequoteai.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            finish() // After registration, go back to login
        }
    }
}
