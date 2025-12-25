package com.example.realtimeprofileanalyzer.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimeprofileanalyzer.databinding.ActivityFingerprintAuthBinding

class FingerprintAuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFingerprintAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFingerprintAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAuthenticate.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}
