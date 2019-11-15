package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.room.entity.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entity.RecipeEntity

@JsonAdapter(RecipeDeserializer::class)
data class RecipeResponse(
    val success: Success,
    val descriptionList: List<DescriptionEntity>,
    val recipesList: List<RecipeEntity>
)

data class LocalizedType(
    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
) {
    fun stringIsNotEmpty() = en.isNotEmpty() && ru.isNotEmpty()
}
