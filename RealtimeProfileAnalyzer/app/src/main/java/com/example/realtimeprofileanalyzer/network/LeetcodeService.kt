package com.example.realtimeprofileanalyzer.network

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LeetcodeService {

    @Headers("Content-Type: application/json")
    @POST("graphql")
    fun getUserStats(@Body body: RequestBody): Call<LeetcodeResponse>
}
