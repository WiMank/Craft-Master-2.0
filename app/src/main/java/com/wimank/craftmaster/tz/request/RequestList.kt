package com.wimank.craftmaster.tz.request

import com.google.gson.annotations.SerializedName

data class RequestList(
    @field:SerializedName("recipePrimaryKey")
    val recipePrimaryKey: RecipePrimaryKey
)

data class RecipePrimaryKey(

    @field:SerializedName("recipe_name")
    val recipeName: String,

    @field:SerializedName("recipe_image_name")
    val recipeImageName: String
)
