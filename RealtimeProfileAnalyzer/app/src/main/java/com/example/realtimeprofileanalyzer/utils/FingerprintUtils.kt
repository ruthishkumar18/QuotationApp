package com.example.realtimeprofileanalyzer.utils

import android.content.Context
import androidx.biometric.BiometricManager

object FingerprintUtils {

    fun isFingerprintAvailable(context: Context): Boolean {
        val biometricManager = BiometricManager.from(context)
        return biometricManager.canAuthenticate(
            BiometricManager.Authenticators.BIOMETRIC_STRONG
        ) == BiometricManager.BIOMETRIC_SUCCESS
    }
}
