package com.mukesh.machinetask.db.typeConverters

import androidx.room.Entity
import androidx.room.TypeConverter
import com.mukesh.machinetask.common.convertGsonString
import com.mukesh.machinetask.common.convertStringIntoClass

@Entity
class IntegerTypeConverter {

    @TypeConverter
    fun fromString(value: String?): List<Int> = (value ?: "").convertStringIntoClass()

    @TypeConverter
    fun toList(list: List<Int?>?): String = (list ?: emptyList<Int>()).convertGsonString()

}
