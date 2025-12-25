package com.voicequoteai.data.model

data class VoiceResult(
    val rawText: String,
    val language: String = "en-IN",
    val confidence: Float = 0f
)
