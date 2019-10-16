package com.wimank.craftmaster.tz.app.recipe_screen.room

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.recipe_screen.rest.*
import com.wimank.craftmaster.tz.common.room.BaseEntity

@Entity(tableName = "description_craft_recipes", primaryKeys = ["recipeName", "recipeImageName"])
data class DescriptionEntity(

    @SerializedName("recipeAttr")
    val recipeAttr: String,

    @SerializedName("recipeImageName")
    val recipeImageName: String,

    @SerializedName("group")
    val group: String,

    @SerializedName("recipe_name")
    val recipeName: RecipeName,

    @SerializedName("lleftParameter")
    val lleftParameter: LleftParameter,

    @SerializedName("lleftParameterImage")
    val lleftParameterImage: String,

    @SerializedName("rrightParameter")
    val rrightParameter: RrightParameter,

    @SerializedName("rrightParameterText")
    val rrightParameterText: RrightParameterText,

    @SerializedName("descriptionCraft")
    val descriptionCraft: DescriptionCraft,

    @SerializedName("wikiLink")
    val wikiLink: String,

    @SerializedName("vers")
    val vers: Int

) : BaseEntity {
    override fun getVersion() = vers
    override fun getImage() = recipeImageName
}
