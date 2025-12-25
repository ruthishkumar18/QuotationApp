package com.example.voicequoteai.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voicequoteai.data.model.BusinessProfile
import com.example.voicequoteai.data.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _profile = MutableLiveData<BusinessProfile?>()
    val profile: LiveData<BusinessProfile?> = _profile

    fun loadProfile() {
        viewModelScope.launch {
            _profile.value = profileRepository.getProfile()
        }
    }

    fun saveProfile(profile: BusinessProfile) {
        viewModelScope.launch {
            profileRepository.saveProfile(profile)
        }
    }
}
