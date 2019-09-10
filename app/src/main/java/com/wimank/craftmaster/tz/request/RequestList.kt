package com.wimank.craftmaster.tz.request

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class RequestList(
    @field:SerializedName("recipePrimaryKey")
    val recipePrimaryKey: RecipePrimaryKey,

    @SerializedName("recipe_name")
    val recipeName: JsonObject
)

data class RecipePrimaryKey(

    @field:SerializedName("recipe_attr")
    val recipeAttr: String,

    @field:SerializedName("recipe_image_name")
    val recipeImageName: String
)
