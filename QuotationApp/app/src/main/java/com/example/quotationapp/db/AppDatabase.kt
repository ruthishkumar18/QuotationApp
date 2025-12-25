package com.example.quotationapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        // Table for company header image
        db.execSQL(
            "CREATE TABLE ${DatabaseContract.HeaderTable.TABLE_NAME} (" +
                    "${DatabaseContract.HeaderTable.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${DatabaseContract.HeaderTable.COLUMN_IMAGE_URI} TEXT)"
        )

        // Table for component details
        db.execSQL(
            "CREATE TABLE ${DatabaseContract.ComponentsTable.TABLE_NAME} (" +
                    "${DatabaseContract.ComponentsTable.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${DatabaseContract.ComponentsTable.COLUMN_NAME} TEXT, " +
                    "${DatabaseContract.ComponentsTable.COLUMN_PRICE} TEXT, " +
                    "${DatabaseContract.ComponentsTable.COLUMN_IMAGE_URI} TEXT)"
        )

        // Table for saved quotations
        db.execSQL(
            "CREATE TABLE ${DatabaseContract.QuotationTable.TABLE_NAME} (" +
                    "${DatabaseContract.QuotationTable.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${DatabaseContract.QuotationTable.COLUMN_CUSTOMER_NAME} TEXT, " +
                    "${DatabaseContract.QuotationTable.COLUMN_MOBILE} TEXT, " +
                    "${DatabaseContract.QuotationTable.COLUMN_EMAIL} TEXT, " +
                    "${DatabaseContract.QuotationTable.COLUMN_PDF_PATH} TEXT, " +
                    "${DatabaseContract.QuotationTable.COLUMN_DATE} TEXT, " +
                    "${DatabaseContract.QuotationTable.COLUMN_STATUS} TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) {
        if (oldV < 2) {
            db.execSQL("ALTER TABLE ${DatabaseContract.QuotationTable.TABLE_NAME} ADD COLUMN ${DatabaseContract.QuotationTable.COLUMN_STATUS} TEXT DEFAULT 'PENDING'")
        }
    }

    companion object {
        const val DATABASE_NAME = "quotation_app_db.sqlite"
        const val DATABASE_VERSION = 2
    }
}
