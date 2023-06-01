package com.mukesh.machinetask.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mukesh.machinetask.data.local.SmartDto

@Dao
interface SmartDao {

    @Query("SELECT * FROM smart")
    fun getAllSmart(): List<SmartDto>?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSmarts(list: List<SmartDto?>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSmart(smartDao: SmartDto?)


    @Query("DELETE FROM smart")
    suspend fun deleteAllSmarts()

}