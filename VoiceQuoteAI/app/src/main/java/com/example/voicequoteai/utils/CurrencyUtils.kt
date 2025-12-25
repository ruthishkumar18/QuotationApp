package com.voicequoteai.utils

import java.text.NumberFormat
import java.util.Locale

object CurrencyUtils {

    fun formatAmount(amount: Double): String {
        val format = NumberFormat.getCurrencyInstance(
            Locale("en", "IN")
        )
        return format.format(amount)
    }

    fun calculateGST(amount: Double, gstPercent: Int = 18): Double {
        return (amount * gstPercent) / 100
    }

    fun calculateTotal(amount: Double, gstPercent: Int = 18): Double {
        return amount + calculateGST(amount, gstPercent)
    }
}
