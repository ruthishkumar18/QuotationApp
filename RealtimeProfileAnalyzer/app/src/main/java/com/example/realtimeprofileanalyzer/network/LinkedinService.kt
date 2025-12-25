package com.example.realtimeprofileanalyzer.network

import com.example.realtimeprofileanalyzer.models.LinkedinProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LinkedinService {

    @GET("linkedin/analyze")
    fun analyzeLinkedinProfile(
        @Query("profileUrl") profileUrl: String
    ): Call<LinkedinProfile>
}
