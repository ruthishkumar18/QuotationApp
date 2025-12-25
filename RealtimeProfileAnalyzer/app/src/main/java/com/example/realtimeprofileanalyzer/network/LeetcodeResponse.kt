package com.example.realtimeprofileanalyzer.network

data class LeetcodeResponse(
    val data: Data
) {
    data class Data(
        val matchedUser: MatchedUser
    )

    data class MatchedUser(
        val submitStats: SubmitStats
    )

    data class SubmitStats(
        val acSubmissionNum: List<Submission>
    )

    data class Submission(
        val difficulty: String,
        val count: Int
    )
}
