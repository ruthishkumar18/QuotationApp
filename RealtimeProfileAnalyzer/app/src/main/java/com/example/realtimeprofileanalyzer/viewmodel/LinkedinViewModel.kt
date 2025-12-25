package com.example.realtimeprofileanalyzer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realtimeprofileanalyzer.models.LinkedinProfile

class LinkedinViewModel : ViewModel() {

    val result = MutableLiveData<LinkedinProfile>()

    fun analyze(profileId: String, profileUrl: String) {

        val validProfile =
            profileUrl.contains("linkedin.com") || profileId.isNotEmpty()

        val connections = if (validProfile) 500 else 150
        val followers = connections / 2
        val following = followers / 3
        val posts = connections / 20
        val impressions = posts * 120

        val bio =
            "Professional with strong academic background and interest in technology and problem solving."

        val education =
            "Bachelor's / Master's degree in Engineering or related field."

        val experience =
            "Academic projects, internships, and hands-on development experience."

        val score = (connections + posts * 5).coerceAtMost(100)

        val summary =
            "Profile shows good activity and professional structure. " +
                    "Improving featured section, certifications, and post consistency " +
                    "will significantly increase visibility."

        result.value = LinkedinProfile(
            profileUrl = profileUrl.ifEmpty { "https://linkedin.com/in/$profileId" },
            headline = "Professional LinkedIn Profile",
            connections = connections,
            followers = followers,
            following = following,
            posts = posts,
            impressions = impressions,
            bio = bio,
            education = education,
            experience = experience,
            profileScore = score,
            suggestions = summary
        )
    }
}
