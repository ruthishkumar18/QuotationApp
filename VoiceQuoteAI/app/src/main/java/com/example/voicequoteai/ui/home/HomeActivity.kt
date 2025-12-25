package com.example.voicequoteai.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.voicequoteai.R
import com.example.voicequoteai.databinding.ActivityHomeBinding
import com.example.voicequoteai.ui.history.HistoryActivity
import com.example.voicequoteai.ui.profile.BusinessProfileActivity
import com.example.voicequoteai.ui.voice.VoiceInputActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start voice quotation
        binding.btnMic.setOnClickListener {
            startActivity(Intent(this, VoiceInputActivity::class.java))
        }

        // Open quotation history
        binding.btnHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        // Open business profile
        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, BusinessProfileActivity::class.java))
        }
    }
}
