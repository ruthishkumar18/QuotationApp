package com.example.realtimeprofileanalyzer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.realtimeprofileanalyzer.database.DatabaseHelper
import com.example.realtimeprofileanalyzer.utils.ValidationUtils

class AuthViewModel : ViewModel() {

    fun validateLogin(email: String, password: String): Boolean {
        return ValidationUtils.isValidEmail(email) &&
                ValidationUtils.isValidPassword(password)
    }

    fun login(db: DatabaseHelper, email: String, password: String): Boolean {
        return db.loginUser(email, password)
    }

    fun register(db: DatabaseHelper, email: String, password: String): Boolean {
        return db.registerUser(email, password)
    }
}
