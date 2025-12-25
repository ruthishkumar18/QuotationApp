package com.example.realtimeprofileanalyzer.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimeprofileanalyzer.databinding.ActivityGithubResultBinding
import com.example.realtimeprofileanalyzer.viewmodel.GithubViewModel

class GithubResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGithubResultBinding
    private val viewModel: GithubViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("profileId") ?: ""

        viewModel.analyze(username)

        viewModel.result.observe(this) {

            binding.tvScore.text =
                "GitHub Score: ${it.profileScore} / 100"

            binding.tvReport.text = """
                Username: ${it.username}
                Profile URL: ${it.profileUrl}

                Public Repositories: ${it.publicRepos}
                Followers: ${it.followers}
                Following: ${it.following}
                Estimated Commits: ${it.estimatedCommits}

                Summary:
                ${it.summary}
            """.trimIndent()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
