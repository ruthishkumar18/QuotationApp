package com.example.voicequoteai

import android.app.Application
import androidx.room.Room
import com.example.voicequoteai.data.local.AppDatabase

class VoiceQuoteApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        // Initialize Room Database
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "voicequote_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
