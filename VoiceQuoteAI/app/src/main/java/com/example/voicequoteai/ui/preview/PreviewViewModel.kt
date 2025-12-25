package com.example.voicequoteai.ui.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voicequoteai.ai.AIResult
import com.example.voicequoteai.ai.FieldMapper
import com.example.voicequoteai.ai.FollowUpQuestionEngine
import com.example.voicequoteai.ai.NLPExtractor
import com.example.voicequoteai.data.model.Quotation
import com.example.voicequoteai.data.repository.QuotationRepository
import kotlinx.coroutines.launch

class PreviewViewModel(
    private val nlpExtractor: NLPExtractor,
    private val fieldMapper: FieldMapper,
    private val followUpQuestionEngine: FollowUpQuestionEngine,
    private val quotationRepository: QuotationRepository
) : ViewModel() {

    fun parseVoiceText(text: String): AIResult {
        return nlpExtractor.extract(text)
    }

    fun createQuotation(aiResult: AIResult): Quotation {
        return fieldMapper.mapToQuotation(aiResult)
    }

    fun saveQuotation(quotation: Quotation) {
        viewModelScope.launch {
            quotationRepository.saveQuotation(quotation)
        }
    }

}
