package com.wimank.craftmaster.tz.common.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wimank.craftmaster.tz.common.room.entities.Category

class Converters {

    @TypeConverter
    fun categoryToJson(category: Category): String {
        return Gson().toJson(category)
    }

    @TypeConverter
    fun jsonToCategory(value: String): Category {
        return Gson().fromJson(value, Category::class.java)
    }
}