package com.example.realtimeprofileanalyzer.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimeprofileanalyzer.databinding.ActivityLinkedinResultBinding
import com.example.realtimeprofileanalyzer.viewmodel.LinkedinViewModel

class LinkedinResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLinkedinResultBinding
    private val viewModel: LinkedinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinkedinResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profileId = intent.getStringExtra("profileId") ?: ""
        val profileUrl = intent.getStringExtra("profileUrl") ?: ""

        viewModel.analyze(profileId, profileUrl)

        viewModel.result.observe(this) {

            binding.tvScore.text =
                "LinkedIn Score: ${it.profileScore} / 100"

            binding.tvReport.text = """
                Profile URL:
                ${it.profileUrl}

                Connections: ${it.connections}
                Followers: ${it.followers}
                Following: ${it.following}
                Posts: ${it.posts}
                Total Impressions: ${it.impressions}

                Bio:
                ${it.bio}

                Education:
                ${it.education}

                Experience:
                ${it.experience}

                Summary:
                ${it.suggestions}
            """.trimIndent()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
