package com.example.voicequoteai.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.voicequoteai.R
import com.example.voicequoteai.ui.auth.LoginActivity
import com.example.voicequoteai.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Splash screen delay
        Handler(Looper.getMainLooper()).postDelayed({

            // Temporary logic
            // Later you can replace this with login check from SharedPreferences
            val isLoggedIn = false

            if (isLoggedIn) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }

            finish()

        }, 2000) // 2 seconds delay
    }
}
