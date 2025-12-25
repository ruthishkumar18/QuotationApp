package com.example.realtimeprofileanalyzer.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimeprofileanalyzer.databinding.ActivityLeetcodeBinding

class LeetcodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeetcodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeetcodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAnalyze.setOnClickListener {

            val profileId = binding.etProfileId.text.toString().trim()
            val profileUrl = binding.etUrl.text.toString().trim()

            val intent = Intent(this, LeetcodeResultActivity::class.java)
            intent.putExtra("profileId", profileId)
            intent.putExtra("profileUrl", profileUrl)
            startActivity(intent)
        }
    }
}
