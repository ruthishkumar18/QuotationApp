package com.example.voicequoteai.data.repository

import com.example.voicequoteai.VoiceQuoteApplication

object RepositoryProvider {

    private val database = VoiceQuoteApplication.database

    val quotationRepository: QuotationRepository by lazy {
        QuotationRepository(database.quotationDao())
    }

    val profileRepository: ProfileRepository by lazy {
        ProfileRepository(database.profileDao())
    }
}
