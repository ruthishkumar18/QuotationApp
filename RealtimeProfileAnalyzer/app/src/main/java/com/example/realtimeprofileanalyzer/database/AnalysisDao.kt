package com.example.realtimeprofileanalyzer.database

class AnalysisDao(private val db: DatabaseHelper) {

    fun insertAnalysis(
        email: String,
        platform: String,
        score: Int,
        result: String,
        date: String
    ) {
        db.saveAnalysis(email, platform, score, result, date)
    }
}
