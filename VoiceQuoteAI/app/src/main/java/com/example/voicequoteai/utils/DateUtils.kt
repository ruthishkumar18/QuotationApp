package com.voicequoteai.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {

    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat(
            Constants.DATE_FORMAT,
            Locale.getDefault()
        )
        return sdf.format(Date())
    }

    fun getValidityDate(days: Int = Constants.QUOTATION_VALIDITY_DAYS): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, days)

        val sdf = SimpleDateFormat(
            Constants.DATE_FORMAT,
            Locale.getDefault()
        )
        return sdf.format(calendar.time)
    }
}
