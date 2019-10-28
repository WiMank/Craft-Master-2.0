package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.room.RecipeDeserializer
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entitys.ManufacturingDevicesEntity
import com.wimank.craftmaster.tz.app.room.entitys.RecipeEntity

@JsonAdapter(RecipeDeserializer::class)
data class RecipeResponse(
    val success: Success,
    val descriptionList: List<DescriptionEntity>,
    val recipesList: List<RecipeEntity>,
    val manuDevices: List<ManufacturingDevicesEntity>
)

data class LocalizedType(
    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
)
