package com.example.realtimeprofileanalyzer.network

data class GithubRepoResponse(
    val name: String,
    val description: String?,
    val stargazers_count: Int,
    val forks_count: Int
)
