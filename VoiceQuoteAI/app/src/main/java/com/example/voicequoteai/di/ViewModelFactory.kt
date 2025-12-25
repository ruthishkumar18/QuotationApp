package com.example.voicequoteai.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.voicequoteai.ui.preview.PreviewViewModel
import com.example.voicequoteai.ui.history.HistoryViewModel
import com.example.voicequoteai.ui.profile.ProfileViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(PreviewViewModel::class.java) -> {
                PreviewViewModel(
                    AppModule.nlpExtractor,
                    AppModule.fieldMapper,
                    AppModule.followUpQuestionEngine,
                    AppModule.quotationRepository
                ) as T
            }

            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(
                    AppModule.quotationRepository
                ) as T
            }

            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(
                    AppModule.profileRepository
                ) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
