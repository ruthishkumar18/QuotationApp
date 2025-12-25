package com.example.realtimeprofileanalyzer.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimeprofileanalyzer.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLinkedin.setOnClickListener {
            startActivity(Intent(this, LinkedinActivity::class.java))
        }

        binding.btnGithub.setOnClickListener {
            startActivity(Intent(this, GithubActivity::class.java))
        }

        binding.btnLeetcode.setOnClickListener {
            startActivity(Intent(this, LeetcodeActivity::class.java))
        }
    }
}
