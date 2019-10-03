package com.wimank.craftmaster.tz.recipe_screen.rest

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.rest.Success
import com.wimank.craftmaster.tz.recipe_screen.room.McDescriptionEntity
import com.wimank.craftmaster.tz.recipe_screen.room.McRecipeEntity
import com.wimank.craftmaster.tz.recipe_screen.room.RecipeDeserializer

@JsonAdapter(RecipeDeserializer::class)
data class RecipeResponse(

    @SerializedName("success")
    var success: Success,

    @SerializedName("recipesList")
    var recipesList: List<McDescriptionEntity>,

    @SerializedName("blueprint")
    var blueprint: List<McRecipeEntity>
)

data class LleftParameter(
    @SerializedName("en")
    var en: String,

    @SerializedName("ru")
    var ru: String
)

data class RecipeName(
    @SerializedName("en")
    var en: String,

    @SerializedName("ru")
    var ru: String

)

data class DescriptionCraft(

    @SerializedName("en")
    var en: String,

    @SerializedName("ru")
    var ru: String

)