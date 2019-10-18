package com.wimank.craftmaster.tz.common.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wimank.craftmaster.tz.app.rest.*
import com.wimank.craftmaster.tz.app.room.entitys.Category

class Converters {

    @TypeConverter
    fun categoryToJson(category: Category): String {
        return Gson().toJson(category)
    }

    @TypeConverter
    fun jsonToCategory(value: String): Category {
        return Gson().fromJson(value, Category::class.java)
    }

    @TypeConverter
    fun lleftParameterToJson(leftParameter: LeftParameter): String {
        return Gson().toJson(leftParameter)
    }

    @TypeConverter
    fun jsonToLleftParameter(value: String): LeftParameter {
        return Gson().fromJson(value, LeftParameter::class.java)
    }

    @TypeConverter
    fun lleftParameterTextToJson(leftParameterText: LeftParameterText): String {
        return Gson().toJson(leftParameterText)
    }

    @TypeConverter
    fun jsonTolleftParameterText(value: String): LeftParameterText {
        return Gson().fromJson(value, LeftParameterText::class.java)
    }

    @TypeConverter
    fun recipeNameToJson(recipeName: RecipeName): String {
        return Gson().toJson(recipeName)
    }

    @TypeConverter
    fun jsonToRecipeName(value: String): RecipeName {
        return Gson().fromJson(value, RecipeName::class.java)
    }

    @TypeConverter
    fun descriptionCraftToJson(descriptionCraft: DescriptionCraft): String {
        return Gson().toJson(descriptionCraft)
    }

    @TypeConverter
    fun jsonToDescriptionCraft(value: String): DescriptionCraft {
        return Gson().fromJson(value, DescriptionCraft::class.java)
    }

    @TypeConverter
    fun rightParameterToJson(rightParameter: RightParameter): String {
        return Gson().toJson(rightParameter)
    }

    @TypeConverter
    fun jsonToRightParameter(value: String): RightParameter {
        return Gson().fromJson(value, RightParameter::class.java)
    }

    @TypeConverter
    fun rightParameterTextToJson(rightParameterText: RightParameterText): String {
        return Gson().toJson(rightParameterText)
    }

    @TypeConverter
    fun jsonTorightParameterText(value: String): RightParameterText {
        return Gson().fromJson(value, RightParameterText::class.java)
    }
}
