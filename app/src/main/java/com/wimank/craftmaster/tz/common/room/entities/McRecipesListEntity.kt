package com.wimank.craftmaster.tz.common.room.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "mc_recipes_list", primaryKeys = ["recipeAttr", "recipeImageName"])
data class McRecipesListEntity(
    @SerializedName("recipeAttr")
    var recipeAttr: String,

    @SerializedName("recipeImageName")
    var recipeImageName: String,

    @SerializedName("recipeName")
    val recipeName: String
)

