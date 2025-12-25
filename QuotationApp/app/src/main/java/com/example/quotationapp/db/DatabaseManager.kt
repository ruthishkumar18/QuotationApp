package com.example.quotationapp.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.quotationapp.model.ComponentModel
import com.example.quotationapp.model.QuotationModel

class DatabaseManager(context: Context) {

    private val dbHelper = AppDatabase(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun close() {
        db.close()
    }

    // ----------------------------------------------------
    // COMPANY HEADER STORAGE
    // ----------------------------------------------------
    fun saveHeaderImage(uri: String) {
        db.execSQL("DELETE FROM ${DatabaseContract.HeaderTable.TABLE_NAME}")

        val values = ContentValues().apply {
            put(DatabaseContract.HeaderTable.COLUMN_IMAGE_URI, uri)
        }
        db.insert(DatabaseContract.HeaderTable.TABLE_NAME, null, values)
    }

    fun getHeaderImage(): String? {
        val cursor = db.rawQuery(
            "SELECT ${DatabaseContract.HeaderTable.COLUMN_IMAGE_URI} FROM ${DatabaseContract.HeaderTable.TABLE_NAME} LIMIT 1",
            null
        )

        var uri: String? = null
        if (cursor.moveToFirst()) uri = cursor.getString(0)

        cursor.close()
        return uri
    }

    // ----------------------------------------------------
    // COMPONENT CRUD
    // ----------------------------------------------------
    fun insertComponent(model: ComponentModel): Long {
        val values = ContentValues().apply {
            put(DatabaseContract.ComponentsTable.COLUMN_NAME, model.name)
            put(DatabaseContract.ComponentsTable.COLUMN_PRICE, model.price)
            put(DatabaseContract.ComponentsTable.COLUMN_IMAGE_URI, model.imageUri)
        }
        return db.insert(DatabaseContract.ComponentsTable.TABLE_NAME, null, values)
    }

    fun updateComponent(id: Int, name: String, price: String): Boolean {
        val values = ContentValues().apply {
            put(DatabaseContract.ComponentsTable.COLUMN_NAME, name)
            put(DatabaseContract.ComponentsTable.COLUMN_PRICE, price)
        }
        val rows = db.update(
            DatabaseContract.ComponentsTable.TABLE_NAME,
            values,
            "${DatabaseContract.ComponentsTable.COLUMN_ID}=?",
            arrayOf(id.toString())
        )
        return rows > 0
    }

    fun deleteComponent(id: Int) {
        db.delete(
            DatabaseContract.ComponentsTable.TABLE_NAME,
            "${DatabaseContract.ComponentsTable.COLUMN_ID}=?",
            arrayOf(id.toString())
        )
    }

    fun getAllComponents(): MutableList<ComponentModel> {
        val list = mutableListOf<ComponentModel>()

        val cursor = db.rawQuery(
            "SELECT * FROM ${DatabaseContract.ComponentsTable.TABLE_NAME}",
            null
        )

        if (cursor.moveToFirst()) {
            do {
                list.add(
                    ComponentModel(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ComponentsTable.COLUMN_ID)),
                        name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ComponentsTable.COLUMN_NAME)),
                        price = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ComponentsTable.COLUMN_PRICE)),
                        imageUri = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ComponentsTable.COLUMN_IMAGE_URI))
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        return list
    }

    // ----------------------------------------------------
    // QUOTATION STORAGE (WITH STATUS)
    // ----------------------------------------------------
    fun insertQuotation(
        customerName: String,
        customerMobile: String,
        customerEmail: String,
        pdfPath: String,
        date: String
    ): Long {

        val values = ContentValues().apply {
            put(DatabaseContract.QuotationTable.COLUMN_CUSTOMER_NAME, customerName)
            put(DatabaseContract.QuotationTable.COLUMN_MOBILE, customerMobile)
            put(DatabaseContract.QuotationTable.COLUMN_EMAIL, customerEmail)
            put(DatabaseContract.QuotationTable.COLUMN_PDF_PATH, pdfPath)
            put(DatabaseContract.QuotationTable.COLUMN_DATE, date)
            put(DatabaseContract.QuotationTable.COLUMN_STATUS, "PENDING")
        }

        return db.insert(DatabaseContract.QuotationTable.TABLE_NAME, null, values)
    }

    fun getAllQuotations(): MutableList<QuotationModel> {
        val list = mutableListOf<QuotationModel>()

        val cursor = db.rawQuery(
            "SELECT * FROM ${DatabaseContract.QuotationTable.TABLE_NAME} ORDER BY id DESC",
            null
        )

        if (cursor.moveToFirst()) {
            do {
                list.add(
                    QuotationModel(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.QuotationTable.COLUMN_ID)),
                        customerName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.QuotationTable.COLUMN_CUSTOMER_NAME)),
                        customerMobile = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.QuotationTable.COLUMN_MOBILE)),
                        customerEmail = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.QuotationTable.COLUMN_EMAIL)),
                        pdfPath = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.QuotationTable.COLUMN_PDF_PATH)),
                        date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.QuotationTable.COLUMN_DATE)),
                        status = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.QuotationTable.COLUMN_STATUS))
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        return list
    }

    fun updateQuotationStatus(id: Int, status: String): Boolean {
        val values = ContentValues().apply {
            put(DatabaseContract.QuotationTable.COLUMN_STATUS, status)
        }

        val rows = db.update(
            DatabaseContract.QuotationTable.TABLE_NAME,
            values,
            "${DatabaseContract.QuotationTable.COLUMN_ID}=?",
            arrayOf(id.toString())
        )
        return rows > 0
    }

    fun deleteQuotation(id: Int): Boolean {
        val rows = db.delete(
            DatabaseContract.QuotationTable.TABLE_NAME,
            "${DatabaseContract.QuotationTable.COLUMN_ID}=?",
            arrayOf(id.toString())
        )
        return rows > 0
    }
}
