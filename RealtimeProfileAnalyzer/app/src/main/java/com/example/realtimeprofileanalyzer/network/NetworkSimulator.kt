package com.example.realtimeprofileanalyzer.network

import com.example.realtimeprofileanalyzer.models.*

object NetworkSimulator {

    fun getLinkedinResult(url: String): LinkedinProfile {
        return LinkedinProfile(
            profileUrl = url,
            headline = "Aspiring Software Engineer",
            connections = 500,
            followers = 1000,
            following = 200,
            posts = 50,
            impressions = 10000,
            bio = "I am a software engineer with a passion for learning and building new things.",
            education = "B.S. in Computer Science",
            experience = "2 years of experience as a software engineer",
            profileScore = 82,
            suggestions = "This is a great LinkedIn profile."
        )
    }

    fun getGithubResult(url: String): GithubProfile {
        return GithubProfile(
            profileUrl = url,
            username = "testuser",
            publicRepos = 15,
            followers = 100,
            following = 50,
            estimatedCommits = 420,
            profileScore = 76,
            summary = "This is a great GitHub profile."
        )
    }

    fun getLeetcodeResult(url: String): LeetcodeProfile {
        return LeetcodeProfile(
            profileUrl = url,
            username = "testuser",
            easySolved = 120,
            mediumSolved = 60,
            hardSolved = 10,
            totalSolved = 190,
            profileScore = 69,
            summary = "This is a great LeetCode profile."
        )
    }
}
