package com.example.voicequoteai.data.repository

import com.example.voicequoteai.data.local.ProfileDao
import com.example.voicequoteai.data.model.BusinessProfile

class ProfileRepository(
    private val profileDao: ProfileDao
) {

    suspend fun saveProfile(profile: BusinessProfile) {
        profileDao.saveProfile(profile)
    }

    suspend fun getProfile(): BusinessProfile? {
        return profileDao.getProfile()
    }
}
