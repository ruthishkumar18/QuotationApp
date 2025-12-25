package com.example.realtimeprofileanalyzer.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimeprofileanalyzer.databinding.ActivityLinkedinBinding

class LinkedinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLinkedinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinkedinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAnalyze.setOnClickListener {

            val profileId = binding.etProfileId.text.toString().trim()
            val profileUrl = binding.etUrl.text.toString().trim()

            val intent = Intent(this, LinkedinResultActivity::class.java)
            intent.putExtra("profileId", profileId)
            intent.putExtra("profileUrl", profileUrl)
            startActivity(intent)
        }
    }
}
