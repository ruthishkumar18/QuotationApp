package com.example.voicequoteai.data.repository

import com.example.voicequoteai.data.local.QuotationDao
import com.example.voicequoteai.data.model.Quotation

class QuotationRepository(
    private val quotationDao: QuotationDao
) {

    suspend fun saveQuotation(quotation: Quotation) {
        quotationDao.insertQuotation(quotation)
    }

    suspend fun getAllQuotations(): List<Quotation> {
        return quotationDao.getAllQuotations()
    }

    suspend fun clearHistory() {
        quotationDao.clearAll()
    }
}
