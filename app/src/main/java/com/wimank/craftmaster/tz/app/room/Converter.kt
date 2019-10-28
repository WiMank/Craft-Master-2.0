package com.wimank.craftmaster.tz.app.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType

class Converter {

    @TypeConverter
    fun leftParameterToJson(localizedType: LocalizedType): String {
        return Gson().toJson(localizedType)
    }

    @TypeConverter
    fun jsonToLeftParameter(value: String): LocalizedType {
        return Gson().fromJson(value, LocalizedType::class.java)
    }
}
