package com.example.voicequoteai.ai

class NLPExtractor {

    fun extract(text: String): AIResult {

        val lowerText = text.lowercase()

        val clientName = extractClientName(text)
        val service = extractService(text)
        val amount = extractAmount(lowerText)
        val advance = extractAdvance(lowerText)
        val delivery = extractDelivery(lowerText)

        return AIResult(
            clientName = clientName,
            service = service,
            amount = amount,
            advancePercent = advance,
            deliveryTimeline = delivery
        )
    }

    private fun extractClientName(text: String): String {
        val regex = Regex("for (.*?)(,|\\d)", RegexOption.IGNORE_CASE)
        return regex.find(text)?.groupValues?.get(1)?.trim()
            ?: "Client"
    }

    private fun extractService(text: String): String {
        return text.split("for")[0].trim()
    }

    private fun extractAmount(text: String): Double {
        val thousandMatch = Regex("(\\d+)\\s*thousand").find(text)
        if (thousandMatch != null) {
            return thousandMatch.groupValues[1].toDouble() * 1000
        }

        val numberMatch = Regex("(\\d{3,6})").find(text)
        return numberMatch?.value?.toDouble() ?: 0.0
    }

    private fun extractAdvance(text: String): Int? {
        val match = Regex("(\\d+)\\s*percent").find(text)
        return match?.groupValues?.get(1)?.toInt()
    }

    private fun extractDelivery(text: String): String? {
        val match = Regex("(\\d+\\s*(days|weeks))").find(text)
        return match?.value
    }
}
