package com.mukesh.machinetask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mukesh.machinetask.data.local.CoursesDto
import com.mukesh.machinetask.data.local.SmartDto
import com.mukesh.machinetask.data.local.UserDto
import com.mukesh.machinetask.db.dao.SmartDao
import com.mukesh.machinetask.db.dao.UserDao
import com.mukesh.machinetask.db.typeConverters.IntegerTypeConverter
import com.mukesh.machinetask.db.typeConverters.StringListConverter


@Database(
    entities = [SmartDto::class, UserDto::class, CoursesDto::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(StringListConverter::class, IntegerTypeConverter::class)
abstract class MachineTaskDb: RoomDatabase() {

    abstract fun userDao(): UserDao


    abstract fun smartDao(): SmartDao


    abstract fun coursesDto(): com.mukesh.machinetask.db.dao.CoursesDao

}