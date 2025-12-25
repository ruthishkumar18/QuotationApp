package com.example.realtimeprofileanalyzer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realtimeprofileanalyzer.models.LeetcodeProfile
import com.example.realtimeprofileanalyzer.network.ApiClient
import com.example.realtimeprofileanalyzer.network.LeetcodeResponse
import com.example.realtimeprofileanalyzer.network.LeetcodeService
import com.example.realtimeprofileanalyzer.network.leetcodeQuery
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeetcodeViewModel : ViewModel() {

    val result = MutableLiveData<LeetcodeProfile>()

    fun analyze(username: String) {

        val body = leetcodeQuery(username)
            .toRequestBody("application/json".toMediaType())

        val api = ApiClient.leetcodeRetrofit.create(LeetcodeService::class.java)

        api.getUserStats(body).enqueue(object : Callback<LeetcodeResponse> {

            override fun onResponse(
                call: Call<LeetcodeResponse>,
                response: Response<LeetcodeResponse>
            ) {
                val stats = response.body()
                    ?.data
                    ?.matchedUser
                    ?.submitStats
                    ?.acSubmissionNum ?: return

                val easy = stats.firstOrNull { it.difficulty == "Easy" }?.count ?: 0
                val medium = stats.firstOrNull { it.difficulty == "Medium" }?.count ?: 0
                val hard = stats.firstOrNull { it.difficulty == "Hard" }?.count ?: 0
                val total = easy + medium + hard

                val score = (total / 5).coerceAtMost(100)

                val summary =
                    "LeetCode profile shows consistent problem-solving practice. " +
                            "Good progress in easy and medium problems. " +
                            "Solving more hard problems will significantly improve ranking."

                result.value = LeetcodeProfile(
                    username = username,
                    profileUrl = "https://leetcode.com/$username",
                    easySolved = easy,
                    mediumSolved = medium,
                    hardSolved = hard,
                    totalSolved = total,
                    profileScore = score,
                    summary = summary
                )
            }

            override fun onFailure(call: Call<LeetcodeResponse>, t: Throwable) {
                // Silent fail for demo stability
            }
        })
    }
}
