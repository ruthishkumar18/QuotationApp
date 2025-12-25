package com.example.voicequoteai.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.voicequoteai.data.model.Quotation

@Dao
interface QuotationDao {

    @Insert
    suspend fun insertQuotation(quotation: Quotation)

    @Query("SELECT * FROM quotations ORDER BY id DESC")
    suspend fun getAllQuotations(): List<Quotation>

    @Query("DELETE FROM quotations")
    suspend fun clearAll()
}
