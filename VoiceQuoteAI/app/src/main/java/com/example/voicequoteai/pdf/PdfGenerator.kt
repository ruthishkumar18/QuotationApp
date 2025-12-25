package com.example.voicequoteai.pdf

import android.content.Context
import android.graphics.*
import android.graphics.pdf.PdfDocument
import com.example.voicequoteai.data.model.BusinessProfile
import com.example.voicequoteai.data.model.Quotation
import java.io.File
import java.io.FileOutputStream

class PdfGenerator(private val context: Context) {

    fun generateQuotationPdf(
        quotation: Quotation,
        profile: BusinessProfile
    ): File {

        val pdfDocument = PdfDocument()
        val paint = Paint()
        val titlePaint = Paint()

        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        var y = 40

        // Title
        titlePaint.textSize = 20f
        titlePaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        canvas.drawText("QUOTATION", 220f, y.toFloat(), titlePaint)

        y += 40

        // Business Info
        paint.textSize = 12f
        paint.typeface = Typeface.DEFAULT_BOLD
        canvas.drawText(profile.businessName, 40f, y.toFloat(), paint)

        y += 18
        paint.typeface = Typeface.DEFAULT
        canvas.drawText("Phone: ${profile.phone}", 40f, y.toFloat(), paint)

        y += 16
        canvas.drawText("Email: ${profile.email}", 40f, y.toFloat(), paint)

        if (profile.gstNumber.isNotBlank()) {
            y += 16
            canvas.drawText("GSTIN: ${profile.gstNumber}", 40f, y.toFloat(), paint)
        }

        y += 30

        // Client Details
        paint.typeface = Typeface.DEFAULT_BOLD
        canvas.drawText("Client:", 40f, y.toFloat(), paint)

        paint.typeface = Typeface.DEFAULT
        canvas.drawText(quotation.clientName, 100f, y.toFloat(), paint)

        y += 30

        // Table Header
        paint.typeface = Typeface.DEFAULT_BOLD
        canvas.drawText("Description", 40f, y.toFloat(), paint)
        canvas.drawText("Amount (₹)", 400f, y.toFloat(), paint)

        y += 10
        canvas.drawLine(40f, y.toFloat(), 550f, y.toFloat(), paint)
        y += 20

        // Table Content
        paint.typeface = Typeface.DEFAULT
        canvas.drawText(quotation.service, 40f, y.toFloat(), paint)
        canvas.drawText(quotation.amount, 420f, y.toFloat(), paint)

        y += 30

        // Payment & Delivery
        canvas.drawText("Advance: ${quotation.advance}", 40f, y.toFloat(), paint)
        y += 18
        canvas.drawText("Delivery: ${quotation.delivery}", 40f, y.toFloat(), paint)

        y += 30

        // Terms
        paint.typeface = Typeface.DEFAULT_BOLD
        canvas.drawText("Terms & Conditions", 40f, y.toFloat(), paint)

        paint.typeface = Typeface.DEFAULT
        y += 18
        canvas.drawText("• 50% advance required before work starts", 40f, y.toFloat(), paint)
        y += 16
        canvas.drawText("• Balance on completion", 40f, y.toFloat(), paint)
        y += 16
        canvas.drawText("• Quotation valid for 7 days", 40f, y.toFloat(), paint)

        y += 40
        canvas.drawText("Authorized Signatory", 400f, y.toFloat(), paint)

        pdfDocument.finishPage(page)

        val file = File(
            context.getExternalFilesDir(null),
            "Quotation_${quotation.clientName}.pdf"
        )

        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()

        return file
    }
}
