package com.example.realtimeprofileanalyzer.models

data class LeetcodeProfile(
    val username: String,
    val profileUrl: String,
    val easySolved: Int,
    val mediumSolved: Int,
    val hardSolved: Int,
    val totalSolved: Int,
    val profileScore: Int,
    val summary: String
)
