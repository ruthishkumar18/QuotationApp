package com.example.realtimeprofileanalyzer.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<GithubUserResponse>
}
