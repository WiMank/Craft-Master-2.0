package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.room.LocalizedType
import com.wimank.craftmaster.tz.app.room.RecipeDeserializer
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entitys.RecipeEntity

@JsonAdapter(RecipeDeserializer::class)
data class RecipeResponse(
    val success: Success,
    val descriptionList: List<DescriptionEntity>,
    val recipesList: List<RecipeEntity>
)

data class LeftParameter(
    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
)

data class LeftParameterText(
    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
)

data class RecipeName(
    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
) : LocalizedType {
    override fun getEnLocalization() = en
    override fun getRuLocalization() = ru
}

data class DescriptionCraft(

    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
)

data class RightParameter(
    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
)

data class RightParameterText(
    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
)
