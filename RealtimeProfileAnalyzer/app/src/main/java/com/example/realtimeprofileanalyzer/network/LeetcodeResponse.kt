package com.example.realtimeprofileanalyzer.network

data class LeetcodeResponse(val data: LeetcodeData)

data class LeetcodeData(val matchedUser: MatchedUser)

data class MatchedUser(
    val username: String,
    val submitStats: SubmitStats
)

data class SubmitStats(
    val acSubmissionNum: List<SubmissionCount>
)

data class SubmissionCount(
    val difficulty: String,
    val count: Int
)
