package com.mukesh.machinetask.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class UserDto(
    val courses: List<Int>? = null,
    val description: String? = null,
    @PrimaryKey(autoGenerate = false)
    val id: Int = -1,
    val isArchive: Int? = null,
    val isDefault: Int? = null,
    val label: String? = null
)