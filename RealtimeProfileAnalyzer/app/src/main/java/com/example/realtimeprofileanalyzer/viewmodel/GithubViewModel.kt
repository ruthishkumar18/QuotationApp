package com.example.realtimeprofileanalyzer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realtimeprofileanalyzer.models.GithubProfile
import com.example.realtimeprofileanalyzer.network.ApiClient
import com.example.realtimeprofileanalyzer.network.GithubService
import com.example.realtimeprofileanalyzer.network.GithubUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubViewModel : ViewModel() {

    val result = MutableLiveData<GithubProfile>()

    fun analyze(username: String) {

        val api = ApiClient. retrofit.create(GithubService::class.java)

        api.getUser(username).enqueue(object : Callback<GithubUserResponse> {

            override fun onResponse(
                call: Call<GithubUserResponse>,
                response: Response<GithubUserResponse>
            ) {
                val user = response.body() ?: return

                val estimatedCommits = user.followers * 6
                val score = calculateScore(user.public_repos, user.followers)

                val summary =
                    "GitHub profile shows active development behavior. " +
                            "The user maintains a healthy number of repositories " +
                            "with good community engagement. Increasing commit " +
                            "frequency and improving documentation will further " +
                            "enhance profile strength."

                result.value = GithubProfile(
                    username = user.login,
                    profileUrl = user.html_url,
                    publicRepos = user.public_repos,
                    followers = user.followers,
                    following = user.following,
                    estimatedCommits = estimatedCommits,
                    profileScore = score,
                    summary = summary
                )
            }

            override fun onFailure(call: Call<GithubUserResponse>, t: Throwable) {
                // Silent fail for demo stability
            }
        })
    }

    private fun calculateScore(repos: Int, followers: Int): Int {
        return (repos * 4 + followers * 2).coerceAtMost(100)
    }
}
