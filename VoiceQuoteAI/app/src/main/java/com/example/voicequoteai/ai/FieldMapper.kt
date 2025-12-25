package com.example.voicequoteai.ai

import com.example.voicequoteai.data.model.Quotation
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FieldMapper {

    fun mapToQuotation(ai: AIResult): Quotation {

        val date = SimpleDateFormat(
            "dd MMM yyyy",
            Locale.getDefault()
        ).format(Date())

        return Quotation(
            clientName = ai.clientName,
            service = ai.service,
            amount = ai.amount.toString(),
            advance = ai.advancePercent?.let { "$it%" } ?: "Not specified",
            delivery = ai.deliveryTimeline ?: "Not specified",
            date = date
        )
    }
}
