package com.example.quotationapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class AddCompanyHeaderActivity : AppCompatActivity() {

    private lateinit var imgPreview: ImageView
    private var selectedImageUri: Uri? = null

    companion object {
        private const val PICK_IMAGE = 1001
        const val HEADER_FILE_NAME = "company_header.png"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_company_header_activity)

        imgPreview = findViewById(R.id.imgHeaderPreview)
        val btnUpload = findViewById<Button>(R.id.btnUploadHeader)
        val btnSave = findViewById<Button>(R.id.btnSaveHeader)

        btnUpload.setOnClickListener {
            pickImageFromGallery()
        }

        btnSave.setOnClickListener {
            if (selectedImageUri == null) {
                Toast.makeText(this, "Please upload an image first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            saveHeaderImage()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            imgPreview.setImageURI(selectedImageUri)
        }
    }

    /**
     * Saves image to internal storage "filesDir/company_header.png"
     * This path is readable by your PDF generator and fixes header loading issues.
     */
    private fun saveHeaderImage() {
        try {
            val bitmap = uriToBitmap(selectedImageUri!!)
            val saveFile = File(filesDir, HEADER_FILE_NAME)

            val fos = FileOutputStream(saveFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.flush()
            fos.close()

            // Save path to SQLite
            val db = com.example.quotationapp.db.DatabaseManager(this)
            db.saveHeaderImage(saveFile.absolutePath)

            Toast.makeText(this, "Header saved successfully", Toast.LENGTH_LONG).show()
            finish()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error saving header", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uriToBitmap(uri: Uri): Bitmap {
        return if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(contentResolver, uri)
        } else {
            val source = ImageDecoder.createSource(contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        }
    }
}
