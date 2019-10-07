package com.wimank.craftmaster.tz.recipe_screen.room

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.room.BaseEntity
import com.wimank.craftmaster.tz.recipe_screen.rest.DescriptionCraft
import com.wimank.craftmaster.tz.recipe_screen.rest.LleftParameter
import com.wimank.craftmaster.tz.recipe_screen.rest.RecipeName


@Entity(tableName = "description_craft_recipes", primaryKeys = ["recipeName", "recipeImageName"])
data class DescriptionEntity(

    @SerializedName("recipeAttr")
    var recipeAttr: String,

    @SerializedName("recipeImageName")
    var recipeImageName: String,

    @SerializedName("recipe_name")
    var recipeName: RecipeName,

    @SerializedName("lleftParameter")
    var lleftParameter: LleftParameter,

    @SerializedName("lleftParameterImage")
    var lleftParameterImage: String,

    @SerializedName("rrightParameter")
    var rrightParameter: String,

    @SerializedName("descriptionCraft")
    var descriptionCraft: DescriptionCraft,

    @SerializedName("wikiLink")
    var wikiLink: String,

    @SerializedName("vers")
    var vers: Int

) : BaseEntity {
    override fun getVersion() = vers
    override fun getImage() = recipeImageName
}