package com.example.realtimeprofileanalyzer.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimeprofileanalyzer.databinding.ActivityLeetcodeResultBinding
import com.example.realtimeprofileanalyzer.viewmodel.LeetcodeViewModel

class LeetcodeResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeetcodeResultBinding
    private val viewModel: LeetcodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeetcodeResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("profileId") ?: ""

        viewModel.analyze(username)

        viewModel.result.observe(this) {

            binding.tvScore.text =
                "LeetCode Score: ${it.profileScore} / 100"

            binding.tvReport.text = """
                Username: ${it.username}
                Profile URL: ${it.profileUrl}

                Easy Solved: ${it.easySolved}
                Medium Solved: ${it.mediumSolved}
                Hard Solved: ${it.hardSolved}
                Total Solved: ${it.totalSolved}

                Summary:
                ${it.summary}
            """.trimIndent()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
