package com.wimank.craftmaster.tz.recipe_screen.rest

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.rest.Success
import com.wimank.craftmaster.tz.recipe_screen.room.RecipeDeserializer
import com.wimank.craftmaster.tz.recipe_screen.room.entity.McDescriptionEntity
import com.wimank.craftmaster.tz.recipe_screen.room.entity.McRecipeEntity

@JsonAdapter(RecipeDeserializer::class)
data class RecipeResponse(
    var success: Success,
    var descriptionList: List<McDescriptionEntity>,
    var recipesList: List<McRecipeEntity>
)

data class LleftParameter(
    @SerializedName("en")
    var en: String = "",

    @SerializedName("ru")
    var ru: String = ""
)

data class RecipeName(
    @SerializedName("en")
    var en: String = "",

    @SerializedName("ru")
    var ru: String = ""
)

data class DescriptionCraft(

    @SerializedName("en")
    var en: String = "",

    @SerializedName("ru")
    var ru: String = ""
)