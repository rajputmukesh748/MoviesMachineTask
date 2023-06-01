package com.mukesh.machinetask.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mukesh.machinetask.data.local.CoursesDto

@Dao
interface CoursesDao {

    @Query("SELECT * FROM courses")
    fun getAllUsers(): List<CoursesDto>?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(list: List<CoursesDto?>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(userDao: CoursesDto?)


    @Query("SELECT * FROM courses WHERE id IN (:idList)")
    fun findMultipleCourses(idList: List<Int>): List<CoursesDto>


    @Query("DELETE FROM courses")
    suspend fun deleteAllCourses()

}