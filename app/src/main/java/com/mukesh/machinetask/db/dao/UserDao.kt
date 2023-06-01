package com.mukesh.machinetask.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mukesh.machinetask.data.local.UserDto
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<UserDto>?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(list: List<UserDto?>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userDao: UserDto?)


    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()

}