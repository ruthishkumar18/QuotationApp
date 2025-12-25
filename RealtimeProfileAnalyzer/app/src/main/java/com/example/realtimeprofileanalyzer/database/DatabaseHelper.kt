package com.example.realtimeprofileanalyzer.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "profile_analyzer.db"
        const val DATABASE_VERSION = 1

        // USER TABLE
        const val TABLE_USER = "users"
        const val COL_USER_ID = "id"
        const val COL_EMAIL = "email"
        const val COL_PASSWORD = "password"

        // PROFILE TABLE
        const val TABLE_PROFILE = "profiles"
        const val COL_PROFILE_ID = "profile_id"
        const val COL_USER_EMAIL = "user_email"
        const val COL_PLATFORM = "platform"
        const val COL_URL = "url"

        // ANALYSIS TABLE
        const val TABLE_ANALYSIS = "analysis"
        const val COL_ANALYSIS_ID = "analysis_id"
        const val COL_SCORE = "score"
        const val COL_RESULT = "result"
        const val COL_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createUserTable = """
            CREATE TABLE $TABLE_USER (
                $COL_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_EMAIL TEXT UNIQUE,
                $COL_PASSWORD TEXT
            )
        """

        val createProfileTable = """
            CREATE TABLE $TABLE_PROFILE (
                $COL_PROFILE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_USER_EMAIL TEXT,
                $COL_PLATFORM TEXT,
                $COL_URL TEXT
            )
        """

        val createAnalysisTable = """
            CREATE TABLE $TABLE_ANALYSIS (
                $COL_ANALYSIS_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_USER_EMAIL TEXT,
                $COL_PLATFORM TEXT,
                $COL_SCORE INTEGER,
                $COL_RESULT TEXT,
                $COL_DATE TEXT
            )
        """

        db.execSQL(createUserTable)
        db.execSQL(createProfileTable)
        db.execSQL(createAnalysisTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PROFILE")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ANALYSIS")
        onCreate(db)
    }

    // ================= USER METHODS =================

    fun registerUser(email: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COL_EMAIL, email)
        values.put(COL_PASSWORD, password)

        val result = db.insert(TABLE_USER, null, values)
        return result != -1L
    }

    fun loginUser(email: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_USER WHERE $COL_EMAIL=? AND $COL_PASSWORD=?",
            arrayOf(email, password)
        )
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    // ================= PROFILE METHODS =================

    fun saveProfileUrl(email: String, platform: String, url: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COL_USER_EMAIL, email)
        values.put(COL_PLATFORM, platform)
        values.put(COL_URL, url)
        db.insert(TABLE_PROFILE, null, values)
    }

    // ================= ANALYSIS METHODS =================

    fun saveAnalysis(
        email: String,
        platform: String,
        score: Int,
        result: String,
        date: String
    ) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COL_USER_EMAIL, email)
        values.put(COL_PLATFORM, platform)
        values.put(COL_SCORE, score)
        values.put(COL_RESULT, result)
        values.put(COL_DATE, date)

        db.insert(TABLE_ANALYSIS, null, values)
    }
}
