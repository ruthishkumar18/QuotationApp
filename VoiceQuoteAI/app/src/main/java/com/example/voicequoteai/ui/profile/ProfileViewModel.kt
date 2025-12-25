package com.example.voicequoteai.ui.profile

import androidx.lifecycle.ViewModel
import com.example.voicequoteai.data.model.BusinessProfile
import com.example.voicequoteai.data.repository.ProfileRepository

class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    suspend fun getProfile() = profileRepository.getProfile()

    suspend fun saveProfile(profile: BusinessProfile) {
        profileRepository.saveProfile(profile)
    }
}
