package com.wimank.craftmaster.tz.recipe_screen.room.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.room.entities.BaseEntity
import com.wimank.craftmaster.tz.recipe_screen.rest.DescriptionCraft
import com.wimank.craftmaster.tz.recipe_screen.rest.LleftParameter
import com.wimank.craftmaster.tz.recipe_screen.rest.RecipeName


@Entity(tableName = "mc_description", primaryKeys = ["recipeName", "recipeImageName"])
data class McDescriptionEntity(

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