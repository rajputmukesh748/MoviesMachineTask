package com.mukesh.machinetask.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "courses")
data class CoursesDto(
    val authorId: Int? = null,
    val cdDownloads: Double? = null,
    val curriculumTags: List<String>? = null,
    val downloadId: Int? = null,
    val educator: String? = null,
    @PrimaryKey(autoGenerate = false)
    val id: Int = -1,
    val owned: Int? = null,
    val progressTracking: String? = null,
    val purchaseOrder: String? = null,
    val releaseDate: String? = null,
    val sale: Int? = null,
    val seriesTags: List<String>? = null,
    val skillTags: List<String>? = null,
    val status: Int? = null,
    val styleTags: List<String>? = null,
    val title: String? = null,
    val videoCount: Int? = null,
    val watched: Int? = null
)