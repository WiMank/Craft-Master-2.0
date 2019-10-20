package com.wimank.craftmaster.tz.common.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wimank.craftmaster.tz.app.rest.responses.*
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionMob
import com.wimank.craftmaster.tz.app.room.entitys.MobName
import com.wimank.craftmaster.tz.app.room.entitys.TypeMob

class Converter {

    @TypeConverter
    fun leftParameterToJson(leftParameter: LeftParameter): String {
        return Gson().toJson(leftParameter)
    }

    @TypeConverter
    fun jsonToLeftParameter(value: String): LeftParameter {
        return Gson().fromJson(value, LeftParameter::class.java)
    }

    @TypeConverter
    fun leftParameterTextToJson(leftParameterText: LeftParameterText): String {
        return Gson().toJson(leftParameterText)
    }

    @TypeConverter
    fun jsonToLeftParameterText(value: String): LeftParameterText {
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
    fun jsonToRightParameterText(value: String): RightParameterText {
        return Gson().fromJson(value, RightParameterText::class.java)
    }

    @TypeConverter
    fun jsonToMobName(value: String): MobName {
        return Gson().fromJson(value, MobName::class.java)
    }

    @TypeConverter
    fun mobNameToJson(mobName: MobName): String {
        return Gson().toJson(mobName)
    }

    @TypeConverter
    fun jsonToTypeMob(value: String): TypeMob {
        return Gson().fromJson(value, TypeMob::class.java)
    }

    @TypeConverter
    fun typeMobToJson(typeMob: TypeMob): String {
        return Gson().toJson(typeMob)
    }

    @TypeConverter
    fun jsonDescriptionMob(value: String): DescriptionMob {
        return Gson().fromJson(value, DescriptionMob::class.java)
    }

    @TypeConverter
    fun descriptionMobToJson(descriptionMob: DescriptionMob): String {
        return Gson().toJson(descriptionMob)
    }
}
