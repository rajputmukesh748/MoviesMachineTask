package com.mukesh.machinetask.data.remote


import com.google.gson.annotations.SerializedName
import com.mukesh.machinetask.data.local.CoursesDto
import com.mukesh.machinetask.data.local.SmartDto
import com.mukesh.machinetask.data.local.UserDto

data class ResponseDto(
    @SerializedName("result")
    val result: Result = Result()
) {
    data class Result(
        @SerializedName("collections")
        val collections: Collections? = null,
        @SerializedName("index")
        val index: List<Index>? = null
    ) {
        data class Collections(
            @SerializedName("smart")
            val smart: List<Smart>? = null,
            @SerializedName("user")
            val user: List<User>? = null
        ) {
            data class Smart(
                @SerializedName("courses")
                val courses: List<Int>? = null,
                @SerializedName("description")
                val description: String? = null,
                @SerializedName("id")
                val id: String? = null,
                @SerializedName("is_archive")
                val isArchive: Int? = null,
                @SerializedName("is_default")
                val isDefault: Int? = null,
                @SerializedName("label")
                val label: String? = null,
                @SerializedName("smart")
                val smart: String? = null
            ){
                fun toSmartDto(): SmartDto {
                    return SmartDto(courses, description, id.orEmpty(), isArchive, isDefault, label, smart)
                }
            }

            data class User(
                @SerializedName("courses")
                val courses: List<Int>? = null,
                @SerializedName("description")
                val description: String? = null,
                @SerializedName("id")
                val id: Int? = null,
                @SerializedName("is_archive")
                val isArchive: Int? = null,
                @SerializedName("is_default")
                val isDefault: Int? = null,
                @SerializedName("label")
                val label: String? = null
            ){
                fun toUserDto(): UserDto{
                    return UserDto(courses, description, id ?: -1, isArchive, isDefault, label)
                }
            }
        }

        data class Index(
            @SerializedName("authorid")
            val authorId: Int? = null,
            @SerializedName("cd_downloads")
            val cdDownloads: Int? = null,
            @SerializedName("curriculum_tags")
            val curriculumTags: List<String>? = null,
            @SerializedName("downloadid")
            val downloadId: Int? = null,
            @SerializedName("educator")
            val educator: String? = null,
            @SerializedName("id")
            val id: Int? = null,
            @SerializedName("owned")
            val owned: Int? = null,
            @SerializedName("progress_tracking")
            val progressTracking: Double? = null,
            @SerializedName("purchase_order")
            val purchaseOrder: String? = null,
            @SerializedName("release_date")
            val releaseDate: String? = null,
            @SerializedName("sale")
            val sale: Int? = null,
            @SerializedName("series_tags")
            val seriesTags: List<String>? = null,
            @SerializedName("skill_tags")
            val skillTags: List<String>? = null,
            @SerializedName("status")
            val status: Int? = null,
            @SerializedName("style_tags")
            val styleTags: List<String>? = null,
            @SerializedName("title")
            val title: String? = null,
            @SerializedName("video_count")
            val videoCount: Int? = null,
            @SerializedName("watched")
            val watched: Int? = null
        ){
            fun toCoursesDto(): CoursesDto {
                return CoursesDto(
                    authorId,
                    cdDownloads?.toDouble(),
                    curriculumTags,
                    downloadId,
                    educator,
                    id ?: -1,
                    owned,
                    progressTracking.toString(),
                    purchaseOrder,
                    releaseDate,
                    sale,
                    seriesTags,
                    skillTags,
                    status,
                    styleTags,
                    title,
                    videoCount,
                    watched
                )
            }
        }
    }
}