package com.example.voicequoteai.ui.preview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.example.voicequoteai.ai.AIResult
import com.example.voicequoteai.data.model.Quotation
import com.example.voicequoteai.databinding.ActivityQuotationPreviewBinding
import com.example.voicequoteai.di.AppModule
import com.example.voicequoteai.di.ViewModelFactory
import com.example.voicequoteai.pdf.PdfGenerator
import com.example.voicequoteai.ui.profile.BusinessProfileActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class QuotationPreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuotationPreviewBinding
    private val viewModel: PreviewViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotationPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val voiceText = intent.getStringExtra("VOICE_TEXT") ?: ""

        binding.txtRawVoice.text = voiceText

        val parsedData = viewModel.parseVoiceText(voiceText)

        binding.txtClient.text = parsedData.clientName
        binding.txtService.text = parsedData.service
        binding.txtAmount.text = parsedData.amount.toString()
        binding.txtAdvance.text = parsedData.advancePercent?.let { "$it%" } ?: "Not specified"
        binding.txtDelivery.text = parsedData.deliveryTimeline ?: "Not specified"

        binding.btnConfirm.setOnClickListener {
            val quotation = viewModel.createQuotation(parsedData)
            viewModel.saveQuotation(quotation)
            generateAndSharePdf(quotation)
        }
    }

    private fun generateAndSharePdf(quotation: Quotation) {
        lifecycleScope.launch {
            try {
                val profile = withContext(Dispatchers.IO) {
                    AppModule.profileRepository.getProfile()
                }

                if (profile != null) {
                    val file = withContext(Dispatchers.IO) {
                        PdfGenerator(this@QuotationPreviewActivity).generateQuotationPdf(quotation, profile)
                    }

                    val pdfUri = FileProvider.getUriForFile(
                        this@QuotationPreviewActivity,
                        "com.example.voicequoteai.provider",
                        file
                    )

                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "application/pdf"
                        putExtra(Intent.EXTRA_STREAM, pdfUri)
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }

                    startActivity(Intent.createChooser(shareIntent, "Share Quotation"))
                    finish()
                } else {
                    Toast.makeText(this@QuotationPreviewActivity, "Business profile not found. Please create one.", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@QuotationPreviewActivity, BusinessProfileActivity::class.java))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@QuotationPreviewActivity, "Error generating PDF.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
