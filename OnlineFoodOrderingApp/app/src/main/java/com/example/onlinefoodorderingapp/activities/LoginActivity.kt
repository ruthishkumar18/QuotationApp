package com.example.onlinefoodorderingapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinefoodorderingapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            validateLogin()
        }
    }

    private fun validateLogin() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        // Admin Login
        if (email == "admin" && password == "admin@123") {
            startActivity(Intent(this, AdminHomeActivity::class.java))
            finish()
            return
        }

        // Student or Staff Login
        if (!email.endsWith("@gmail.com") && !email.endsWith("@srec.ac.in")) {
            showToast("Invalid email domain")
            return
        }

        val passwordPattern =
            Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}$")

        if (!passwordPattern.matches(password)) {
            showToast("Password does not meet requirements")
            return
        }

        startActivity(Intent(this, UserHomeActivity::class.java))
        finish()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
