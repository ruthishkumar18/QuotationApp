package com.example.quotationapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        findViewById<Button>(R.id.btnAddComponents).setOnClickListener {
            startActivity(Intent(this, AddComponentsActivity::class.java))
        }

        findViewById<Button>(R.id.btnAddCompanyHeader).setOnClickListener {
            startActivity(Intent(this, AddCompanyHeaderActivity::class.java))
        }

        findViewById<Button>(R.id.btnNewQuotation).setOnClickListener {
            startActivity(Intent(this, PrepareQuotationActivity::class.java))
        }

        // ✅ THIS WAS MISSING
        findViewById<Button>(R.id.btnQuotationHistory).setOnClickListener {
            startActivity(Intent(this, QuotationHistoryActivity::class.java))
        }
    }
}
