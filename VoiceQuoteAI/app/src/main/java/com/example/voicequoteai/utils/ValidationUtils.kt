package com.voicequoteai.utils

import android.util.Patterns

object ValidationUtils {

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPhone(phone: String): Boolean {
        return phone.length in 10..13
    }

    fun isNotEmpty(text: String): Boolean {
        return text.trim().isNotEmpty()
    }
}
