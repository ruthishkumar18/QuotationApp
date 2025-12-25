package com.example.voicequoteai.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.voicequoteai.data.model.BusinessProfile

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(profile: BusinessProfile)

    @Query("SELECT * FROM business_profile WHERE id = 1")
    suspend fun getProfile(): BusinessProfile?
}
