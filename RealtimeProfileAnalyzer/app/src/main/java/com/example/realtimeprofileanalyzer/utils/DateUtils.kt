package com.example.realtimeprofileanalyzer.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date())
    }
}
