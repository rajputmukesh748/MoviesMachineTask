package com.mukesh.machinetask.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "smart")
data class SmartDto(
    val courses: List<Int>? = null,
    val description: String? = null,
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    val isArchive: Int? = null,
    val isDefault: Int? = null,
    val label: String? = null,
    val smart: String? = null,
)