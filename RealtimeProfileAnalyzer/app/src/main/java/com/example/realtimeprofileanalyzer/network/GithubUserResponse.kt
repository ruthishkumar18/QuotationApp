package com.example.realtimeprofileanalyzer.network

data class GithubUserResponse(
    val login: String,
    val html_url: String,
    val public_repos: Int,
    val followers: Int,
    val following: Int
)
