package com.example.voicequoteai.ui.auth

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    fun login(email: String, password: String): Boolean {
        // Dummy login logic
        return email.isNotEmpty() && password.isNotEmpty()
    }
}
