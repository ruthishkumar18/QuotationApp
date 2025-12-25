package com.example.voicequoteai.data.local

import com.example.voicequoteai.data.model.BusinessProfile
import com.example.voicequoteai.data.model.Quotation

class LocalRepository(
    private val database: AppDatabase
) {

    /* Quotation Operations */

    suspend fun saveQuotation(quotation: Quotation) {
        database.quotationDao().insertQuotation(quotation)
    }

    suspend fun getQuotationHistory(): List<Quotation> {
        return database.quotationDao().getAllQuotations()
    }

    /* Business Profile Operations */

    suspend fun saveBusinessProfile(profile: BusinessProfile) {
        database.profileDao().saveProfile(profile)
    }

    suspend fun getBusinessProfile(): BusinessProfile? {
        return database.profileDao().getProfile()
    }
}
