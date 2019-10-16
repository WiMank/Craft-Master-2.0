package com.wimank.craftmaster.tz.common.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wimank.craftmaster.tz.app.categories_screen.room.Category
import com.wimank.craftmaster.tz.app.recipe_screen.rest.*

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
    fun lleftParameterToJson(lleftParameter: LleftParameter): String {
        return Gson().toJson(lleftParameter)
    }

    @TypeConverter
    fun jsonToLleftParameter(value: String): LleftParameter {
        return Gson().fromJson(value, LleftParameter::class.java)
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
    fun rightParameterToJson(rrightParameter: RrightParameter): String {
        return Gson().toJson(rrightParameter)
    }

    @TypeConverter
    fun jsonToRightParameter(value: String): RrightParameter {
        return Gson().fromJson(value, RrightParameter::class.java)
    }

    @TypeConverter
    fun rightParameterTextToJson(rrightParameterText: RrightParameterText): String {
        return Gson().toJson(rrightParameterText)
    }

    @TypeConverter
    fun jsonTorightParameterText(value: String): RrightParameterText {
        return Gson().fromJson(value, RrightParameterText::class.java)
    }
}
