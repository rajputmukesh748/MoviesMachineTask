package com.mukesh.machinetask.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "smart")
data class SmartDto(
    var courses: List<Int>? = emptyList(),
    var description: String? = null,
    @PrimaryKey(autoGenerate = false)
    var id: String = "",
    @ColumnInfo("isDrchive")
    var archive: Int? = null,
    @ColumnInfo("isDefault")
    var defaultValue: Int? = null,
    var label: String? = "",
    var smart: String? = "",
    @Ignore
    var coursesList: ArrayList<CoursesDto>? = ArrayList()
)