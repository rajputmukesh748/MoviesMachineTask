package com.mukesh.machinetask.db.typeConverters

import androidx.room.Entity
import androidx.room.TypeConverter
import com.mukesh.machinetask.common.convertGsonString
import com.mukesh.machinetask.common.convertStringIntoClass

@Entity
class StringListConverter {

    @TypeConverter
    fun fromString(value: String?): List<String> =
        (value ?: "").convertStringIntoClass()

    @TypeConverter
    fun toList(list: List<String?>?): String =
        (list ?: emptyList<String>()).convertGsonString()

}
