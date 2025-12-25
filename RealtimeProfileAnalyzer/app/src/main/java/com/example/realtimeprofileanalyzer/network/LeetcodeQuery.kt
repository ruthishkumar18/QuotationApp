package com.example.realtimeprofileanalyzer.network

fun leetcodeQuery(username: String): String {
    return """
        {
          "query": "query getUserProfile { matchedUser(username: \"$username\") { submitStats { acSubmissionNum { difficulty count } } } }"
        }
    """.trimIndent()
}
