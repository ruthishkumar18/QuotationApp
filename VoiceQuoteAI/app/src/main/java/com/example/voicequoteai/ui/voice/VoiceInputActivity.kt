package com.example.voicequoteai.ui.voice

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.voicequoteai.R
import com.example.voicequoteai.ai.SpeechToTextManager
import com.example.voicequoteai.databinding.ActivityVoiceInputBinding
import com.example.voicequoteai.ui.preview.QuotationPreviewActivity
import com.example.voicequoteai.utils.PermissionUtils

class VoiceInputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVoiceInputBinding
    private lateinit var speechToTextManager: SpeechToTextManager

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            startSpeechRecognition()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoiceInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        speechToTextManager = SpeechToTextManager(this) {
            binding.txtStatus.text = "Processing..."
            if (it.isNotBlank()) {
                val intent = Intent(this, QuotationPreviewActivity::class.java).apply {
                    putExtra("VOICE_TEXT", it)
                }
                startActivity(intent)
                finish()
            } else {
                binding.txtStatus.text = "Failed. Try again."
                binding.btnTryAgain.visibility = View.VISIBLE
            }
        }

        binding.btnMic.setOnClickListener {
            if (PermissionUtils.hasRecordAudioPermission(this)) {
                startSpeechRecognition()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }

        binding.btnTryAgain.setOnClickListener {
            startSpeechRecognition()
        }
    }

    private fun startSpeechRecognition() {
        if (speechToTextManager.isRecognitionAvailable()) {
            binding.txtStatus.text = "Listening..."
            binding.btnTryAgain.visibility = View.GONE
            speechToTextManager.startListening()
        } else {
            binding.txtStatus.text = "Speech recognition not available"
            binding.btnTryAgain.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speechToTextManager.stop()
    }
}
