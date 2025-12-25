package com.example.quotationapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.quotationapp.db.DatabaseManager
import com.example.quotationapp.model.ComponentModel
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.pdf.PdfDocument

class QuotationPDFActivity : AppCompatActivity() {

    private lateinit var db: DatabaseManager
    private lateinit var btnOpen: Button
    private lateinit var btnShare: Button
    private lateinit var tvMsg: TextView

    private lateinit var custName: String
    private lateinit var custMobile: String
    private lateinit var custEmail: String

    data class SelectedItem(val model: ComponentModel, val qty: Int)
    private val selectedItems = mutableListOf<SelectedItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_quotation)

        db = DatabaseManager(this)

        btnOpen = findViewById(R.id.btnOpenPDF)
        btnShare = findViewById(R.id.btnSharePDF)
        tvMsg = findViewById(R.id.tvPDFMessage)

        custName = intent.getStringExtra("customerName")!!
        custMobile = intent.getStringExtra("customerMobile")!!
        custEmail = intent.getStringExtra("customerEmail")!!

        extractSelectedItems()

        val pdf = generatePDF()
        if (pdf == null) {
            Toast.makeText(this, "Failed to generate PDF", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val date = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date())
        db.insertQuotation(custName, custMobile, custEmail, pdf.absolutePath, date)

        tvMsg.text = "Quotation created successfully!"

        btnOpen.setOnClickListener { openPDF(pdf) }
        btnShare.setOnClickListener { sharePDF(pdf) }
    }

    private fun extractSelectedItems() {
        val arr = intent.getSerializableExtra("selectedList") as ArrayList<HashMap<String, String>>
        arr.forEach {
            val model = ComponentModel(
                id = it["id"]!!.toInt(),
                name = it["name"]!!,
                price = it["price"]!!,
                imageUri = it["imageUri"]!!
            )
            selectedItems.add(SelectedItem(model, it["qty"]!!.toInt()))
        }
    }

    // -------------------------------------------------------------------
    // WRAP TEXT FOR COMPONENT NAME
    // -------------------------------------------------------------------
    private fun wrap(text: String, maxWidth: Float, paint: Paint): List<String> {
        val words = text.split(" ")
        val lines = mutableListOf<String>()
        var current = ""

        for (word in words) {
            val test = if (current.isEmpty()) word else "$current $word"
            if (paint.measureText(test) <= maxWidth) {
                current = test
            } else {
                lines.add(current)
                current = word
            }
        }

        if (current.isNotEmpty()) lines.add(current)
        return lines
    }

    // -------------------------------------------------------------------
    // PDF GENERATION
    // -------------------------------------------------------------------
    private fun generatePDF(): File? {
        return try {
            val pdf = PdfDocument()
            val width = 595
            val height = 842
            val margin = 40f

            val pageInfo = PdfDocument.PageInfo.Builder(width, height, 1).create()
            val page = pdf.startPage(pageInfo)
            val canvas = page.canvas

            val border = Paint().apply {
                color = Color.BLACK
                strokeWidth = 2f
                style = Paint.Style.STROKE
            }

            val bold = Paint().apply {
                color = Color.BLACK
                textSize = 18f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }

            val textPaint = Paint().apply {
                color = Color.BLACK
                textSize = 14f
            }

            // Draw outer page border
            canvas.drawRect(margin, margin, width - margin, height - margin, border)

            var y = margin + 30f

            // -------------------------------------------------------------------
            // CUSTOMER DETAILS
            // -------------------------------------------------------------------
            canvas.drawText("Customer Details", margin + 10, y, bold)
            y += 30
            canvas.drawText("Name   : $custName", margin + 10, y, textPaint); y += 20
            canvas.drawText("Mobile : $custMobile", margin + 10, y, textPaint); y += 20
            canvas.drawText("Email  : $custEmail", margin + 10, y, textPaint); y += 35

            // -------------------------------------------------------------------
            // TABLE HEADER
            // -------------------------------------------------------------------
            canvas.drawText("Quotation Items", margin + 10, y, bold)
            y += 25

            // FIXED COLUMN WIDTHS for PERFECT ALIGNMENT
            val colSN = margin + 10     // width 40
            val colName = margin + 50   // width 220
            val colPrice = margin + 270 // width 60
            val colQty = margin + 330   // width 50
            val colTotal = margin + 380 // width 100
            val tableRight = margin + 480

            val rowH = 28

            // Header rectangle
            canvas.drawRect(colSN, y, tableRight, y + rowH, border)

            // Vertical dividers
            canvas.drawLine(colName, y, colName, y + rowH, border)
            canvas.drawLine(colPrice, y, colPrice, y + rowH, border)
            canvas.drawLine(colQty, y, colQty, y + rowH, border)
            canvas.drawLine(colTotal, y, colTotal, y + rowH, border)

            // Header labels
            canvas.drawText("S.No", colSN + 5, y + 20, textPaint)
            canvas.drawText("Component Name", colName + 5, y + 20, textPaint)
            canvas.drawText("Price", colPrice + 5, y + 20, textPaint)
            canvas.drawText("Qty", colQty + 5, y + 20, textPaint)
            canvas.drawText("Total", colTotal + 5, y + 20, textPaint)

            y += rowH

            // -------------------------------------------------------------------
            // TABLE ROWS (PERFECTLY ALIGNED)
            // -------------------------------------------------------------------
            var subtotal = 0.0
            var sn = 1

            selectedItems.forEach { item ->

                val total = item.model.price.toDouble() * item.qty
                subtotal += total

                val wrapped = wrap(item.model.name, colPrice - colName - 15, textPaint)

                val rowHeight = wrapped.size * 18 + 12

                // full row box
                canvas.drawRect(colSN, y, tableRight, y + rowHeight, border)

                // vertical lines (PERFECT ALIGNMENT)
                canvas.drawLine(colName, y, colName, y + rowHeight, border)
                canvas.drawLine(colPrice, y, colPrice, y + rowHeight, border)
                canvas.drawLine(colQty, y, colQty, y + rowHeight, border)
                canvas.drawLine(colTotal, y, colTotal, y + rowHeight, border)

                // S.No, price, qty, total
                canvas.drawText(sn.toString(), colSN + 5, y + 20, textPaint)
                canvas.drawText("₹${item.model.price}", colPrice + 5, y + 20, textPaint)
                canvas.drawText(item.qty.toString(), colQty + 5, y + 20, textPaint)
                canvas.drawText("₹$total", colTotal + 5, y + 20, textPaint)

                // component name with wrapping
                var yy = y + 20
                for (line in wrapped) {
                    canvas.drawText(line, colName + 5, yy, textPaint)
                    yy += 18
                }

                y += rowHeight
                sn++
            }

            y += 35

            // -------------------------------------------------------------------
            // PRICE SUMMARY TABLE
            // -------------------------------------------------------------------
            canvas.drawText("Price Summary", margin + 10, y, bold)
            y += 25

            val sumLeft = margin + 250
            val sumRight = margin + 480

            fun row(label: String, value: String) {
                canvas.drawRect(sumLeft, y, sumRight, y + rowH, border)

                // separator
                canvas.drawLine(sumLeft + 110, y, sumLeft + 110, y + rowH, border)

                canvas.drawText(label, sumLeft + 10, y + 20, textPaint)
                canvas.drawText(value, sumLeft + 120, y + 20, textPaint)

                y += rowH
            }

            val gst = subtotal * 0.18
            val grand = subtotal + gst

            row("Subtotal", "₹%.2f".format(subtotal))
            row("GST (18%)", "₹%.2f".format(gst))
            row("Grand Total", "₹%.2f".format(grand))

            y += 35

            // -------------------------------------------------------------------
            // TERMS
            // -------------------------------------------------------------------
            canvas.drawText("Terms & Conditions", margin + 10, y, bold)
            y += 25

            val terms = listOf(
                "Quotation valid for 30 days.",
                "Prices include GST as applicable.",
                "Delivery within 7–10 business days.",
                "Payment terms: 50% advance."
            )

            terms.forEach {
                canvas.drawText("• $it", margin + 10, y, textPaint)
                y += 18
            }

            pdf.finishPage(page)

            val file = File(getExternalFilesDir(null), "quotation_${System.currentTimeMillis()}.pdf")
            pdf.writeTo(FileOutputStream(file))
            pdf.close()
            file

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun openPDF(file: File) {
        try {
            val uri = FileProvider.getUriForFile(this, "${packageName}.provider", file)
            val i = Intent(Intent.ACTION_VIEW)
            i.setDataAndType(uri, "application/pdf")
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(i)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No PDF viewer found!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sharePDF(file: File) {
        val uri = FileProvider.getUriForFile(this, "${packageName}.provider", file)
        val i = Intent(Intent.ACTION_SEND)
        i.type = "application/pdf"
        i.putExtra(Intent.EXTRA_STREAM, uri)
        i.putExtra(Intent.EXTRA_TEXT, "Quotation PDF Attached")
        i.putExtra(Intent.EXTRA_SUBJECT, "Quotation Document")
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(i, "Share Using"))
    }
}
