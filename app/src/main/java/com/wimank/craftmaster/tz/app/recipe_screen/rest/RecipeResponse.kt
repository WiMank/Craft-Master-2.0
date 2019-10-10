package com.wimank.craftmaster.tz.app.recipe_screen.rest

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.recipe_screen.room.DescriptionEntity
import com.wimank.craftmaster.tz.app.recipe_screen.room.RecipeDeserializer
import com.wimank.craftmaster.tz.app.recipe_screen.room.RecipeEntity
import com.wimank.craftmaster.tz.common.rest.Success

@JsonAdapter(RecipeDeserializer::class)
data class RecipeResponse(
    val success: Success,
    val descriptionList: List<DescriptionEntity>,
    val recipesList: List<RecipeEntity>
)

data class LleftParameter(
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
)

data class DescriptionCraft(

    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
)
