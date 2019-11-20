package com.wimank.craftmaster.tz.app.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType

class Converter {

    @TypeConverter
    fun localizedTypeToJson(localizedType: LocalizedType): String {
        return Gson().toJson(localizedType)
    }

    @TypeConverter
    fun jsonToLocalizedType(value: String): LocalizedType {
        return Gson().fromJson(value, LocalizedType::class.java)
    }
}
