package com.example.realtimeprofileanalyzer.models

data class LinkedinProfile(
    val profileUrl: String,
    val headline: String,
    val connections: Int,
    val followers: Int,
    val following: Int,
    val posts: Int,
    val impressions: Int,
    val bio: String,
    val education: String,
    val experience: String,
    val profileScore: Int,
    val suggestions: String
)
