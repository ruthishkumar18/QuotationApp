package com.example.realtimeprofileanalyzer.models

data class GithubProfile(
    val username: String,
    val profileUrl: String,
    val publicRepos: Int,
    val followers: Int,
    val following: Int,
    val estimatedCommits: Int,
    val profileScore: Int,
    val summary: String
)
