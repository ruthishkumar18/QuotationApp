package com.example.realtimeprofileanalyzer.models

data class AnalysisHistory(
    val id: Int = 0,
    val email: String,
    val platform: String,
    val score: Int,
    val resultSummary: String,
    val date: String
)
