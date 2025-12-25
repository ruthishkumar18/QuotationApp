package com.example.voicequoteai.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.voicequoteai.data.model.BusinessProfile
import com.example.voicequoteai.data.model.Quotation

@Database(
    entities = [
        Quotation::class,
        BusinessProfile::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun quotationDao(): QuotationDao
    abstract fun profileDao(): ProfileDao
}
