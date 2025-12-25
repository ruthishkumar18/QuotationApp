package com.example.voicequoteai.ui.history

import androidx.lifecycle.ViewModel
import com.example.voicequoteai.data.repository.QuotationRepository

class HistoryViewModel(
    private val quotationRepository: QuotationRepository
) : ViewModel() {

    suspend fun getHistory() = quotationRepository.getAllQuotations()
}
