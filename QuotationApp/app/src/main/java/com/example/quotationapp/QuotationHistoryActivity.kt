package com.example.quotationapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotationapp.adapter.QuotationHistoryAdapter
import com.example.quotationapp.db.DatabaseManager
import com.example.quotationapp.model.QuotationModel
import java.io.File

class   QuotationHistoryActivity : AppCompatActivity() {

    private lateinit var db: DatabaseManager
    private lateinit var adapter: QuotationHistoryAdapter
    private lateinit var rvHistory: RecyclerView
    private lateinit var ivBack: ImageView

    private val list = mutableListOf<QuotationModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quotation_history_activity)

        db = DatabaseManager(this)

        rvHistory = findViewById(R.id.rvQuotationHistory)
        ivBack = findViewById(R.id.ivBack)

        ivBack.setOnClickListener { finish() }

        rvHistory.layoutManager = LinearLayoutManager(this)

        adapter = QuotationHistoryAdapter(
            list,

            onOpen = { quotation ->
                openPDF(quotation.pdfPath)
            },

            onShare = { quotation ->
                sharePDF(quotation.pdfPath)
            },

            onAccept = { quotation ->
                // 1️⃣ Update status
                db.updateQuotationStatus(quotation.id, "ACCEPTED")

                // 2️⃣ UPI payment link (replace with your real UPI)
                val upiLink =
                    "upi://pay?pa=ruthishkumarg-1@oksbi&pn=SBI&cu=INR"

                // 3️⃣ Open QR screen
                val intent = Intent(this, PaymentQRActivity::class.java)
                intent.putExtra("qrUri", upiLink)
                startActivity(intent)

                // 4️⃣ Refresh list
                loadData()
            },

            onReject = { quotation ->
                db.updateQuotationStatus(quotation.id, "REJECTED")
                loadData()
            }
        )

        rvHistory.adapter = adapter
        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    // -----------------------------------
    // LOAD DATA
    // -----------------------------------
    private fun loadData() {
        list.clear()
        list.addAll(db.getAllQuotations())
        adapter.notifyDataSetChanged()
    }

    // -----------------------------------
    // OPEN PDF
    // -----------------------------------
    private fun openPDF(path: String) {
        try {
            val file = File(path)
            if (!file.exists()) {
                Toast.makeText(this, "PDF file not found", Toast.LENGTH_SHORT).show()
                return
            }

            val uri = FileProvider.getUriForFile(
                this,
                "${packageName}.provider",
                file
            )

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/pdf")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "No PDF viewer installed", Toast.LENGTH_SHORT).show()
        }
    }

    // -----------------------------------
    // SHARE PDF
    // -----------------------------------
    private fun sharePDF(path: String) {
        try {
            val file = File(path)
            if (!file.exists()) {
                Toast.makeText(this, "PDF file not found", Toast.LENGTH_SHORT).show()
                return
            }

            val uri = FileProvider.getUriForFile(
                this,
                "${packageName}.provider",
                file
            )

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "application/pdf"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            startActivity(Intent.createChooser(intent, "Share Quotation PDF"))
        } catch (e: Exception) {
            Toast.makeText(this, "Unable to share PDF", Toast.LENGTH_SHORT).show()
        }
    }
}
