package com.example.realtimeprofileanalyzer.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimeprofileanalyzer.databinding.ActivityGithubBinding

class GithubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGithubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAnalyze.setOnClickListener {

            val profileId = binding.etProfileId.text.toString().trim()
            val profileUrl = binding.etUrl.text.toString().trim()

            val intent = Intent(this, GithubResultActivity::class.java)
            intent.putExtra("profileId", profileId)
            intent.putExtra("profileUrl", profileUrl)
            startActivity(intent)
        }
    }
}
