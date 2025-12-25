package com.example.realtimeprofileanalyzer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val GITHUB_BASE_URL = "https://api.github.com/"
    private const val LEETCODE_BASE_URL = "https://leetcode.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val leetcodeRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(LEETCODE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
