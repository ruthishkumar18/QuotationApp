package com.example.quotationapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotationapp.adapter.ComponentsAdapter
import com.example.quotationapp.db.DatabaseManager
import com.example.quotationapp.model.ComponentModel
import java.io.File
import java.io.FileOutputStream

class AddComponentsActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPrice: EditText
    private lateinit var imgPreview: ImageView
    private lateinit var btnChoose: Button
    private lateinit var btnAdd: Button
    private lateinit var rvDashboard: androidx.recyclerview.widget.RecyclerView

    private lateinit var db: DatabaseManager
    private lateinit var adapter: ComponentsAdapter
    private val list = mutableListOf<ComponentModel>()

    private var selectedImageUri: Uri? = null

    companion object {
        private const val PICK_IMAGE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_components)

        db = DatabaseManager(this)

        etName = findViewById(R.id.etComponentName)
        etPrice = findViewById(R.id.etComponentPrice)
        imgPreview = findViewById(R.id.imgPreview)
        btnChoose = findViewById(R.id.btnChooseImage)
        btnAdd = findViewById(R.id.btnAddComponent)
        rvDashboard = findViewById(R.id.rvComponentsDashboard)

        list.addAll(db.getAllComponents())

        adapter = ComponentsAdapter(
            list,
            onEdit = { showEditDialog(it) },
            onDelete = { deleteComponent(it) }
        )

        rvDashboard.layoutManager = LinearLayoutManager(this)
        rvDashboard.adapter = adapter

        btnChoose.setOnClickListener { pickImage() }
        btnAdd.setOnClickListener { addComponent() }
    }

    private fun pickImage() {
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

    private fun addComponent() {
        val name = etName.text.toString().trim()
        val price = etPrice.text.toString().trim()

        if (name.isEmpty() || price.isEmpty() || selectedImageUri == null) {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
            return
        }

        val savedUri = saveImage(selectedImageUri!!)
        val id = db.insertComponent(ComponentModel(0, name, price, savedUri)).toInt()

        list.add(ComponentModel(id, name, price, savedUri))
        adapter.notifyDataSetChanged()

        etName.text.clear()
        etPrice.text.clear()
        imgPreview.setImageResource(0)
        selectedImageUri = null
    }

    private fun saveImage(uri: Uri): String {
        val bitmap: Bitmap = if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(contentResolver, uri)
        } else {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri))
        }

        val file = File(filesDir, "cmp_${System.currentTimeMillis()}.png")
        val out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        out.flush()
        out.close()
        return file.absolutePath
    }

    private fun deleteComponent(item: ComponentModel) {
        db.deleteComponent(item.id)
        list.remove(item)
        adapter.notifyDataSetChanged()
    }

    private fun showEditDialog(item: ComponentModel) {
        val dialog = AlertDialog.Builder(this).create()
        val view = layoutInflater.inflate(R.layout.dialog_edit_component, null)

        val etName = view.findViewById<EditText>(R.id.etEditName)
        val etPrice = view.findViewById<EditText>(R.id.etEditPrice)

        etName.setText(item.name)
        etPrice.setText(item.price)

        dialog.setView(view)

        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Save") { _, _ ->
            val newName = etName.text.toString()
            val newPrice = etPrice.text.toString()

            db.updateComponent(item.id, newName, newPrice)

            val index = list.indexOfFirst { it.id == item.id }
            if (index != -1) {
                list[index] = ComponentModel(item.id, newName, newPrice, item.imageUri)
                adapter.notifyItemChanged(index)
            }
        }

        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel") { _, _ -> }
        dialog.show()
    }
}
