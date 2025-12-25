package com.example.voicequoteai.di

import android.content.Context
import com.example.voicequoteai.VoiceQuoteApplication
import com.example.voicequoteai.ai.FollowUpQuestionEngine
import com.example.voicequoteai.ai.NLPExtractor
import com.example.voicequoteai.ai.FieldMapper
import com.example.voicequoteai.data.repository.ProfileRepository
import com.example.voicequoteai.data.repository.QuotationRepository
import com.example.voicequoteai.pdf.PdfGenerator

object AppModule {

    /* Database */

    private val database
        get() = VoiceQuoteApplication.database

    /* Repositories */

    val quotationRepository: QuotationRepository by lazy {
        QuotationRepository(database.quotationDao())
    }

    val profileRepository: ProfileRepository by lazy {
        ProfileRepository(database.profileDao())
    }

    /* AI Components */

    val nlpExtractor: NLPExtractor by lazy {
        NLPExtractor()
    }

    val fieldMapper: FieldMapper by lazy {
        FieldMapper()
    }

    val followUpQuestionEngine: FollowUpQuestionEngine by lazy {
        FollowUpQuestionEngine()
    }

    /* PDF */

    fun providePdfGenerator(context: Context): PdfGenerator {
        return PdfGenerator(context)
    }
}
