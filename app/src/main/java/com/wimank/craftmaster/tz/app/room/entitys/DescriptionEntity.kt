package com.wimank.craftmaster.tz.app.room.entitys

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.rest.responses.*

@Entity(tableName = "description_craft_recipes", primaryKeys = ["recipeName", "recipeImageName"])
data class DescriptionEntity(

    @SerializedName("recipeAttr")
    val recipeAttr: String,

    @SerializedName("recipeImageName")
    val recipeImageName: String,

    @SerializedName("modification")
    val modification: String,

    @SerializedName("recipe_name")
    val recipeName: RecipeName,

    @SerializedName("leftParameter")
    val leftParameter: LeftParameter,

    @SerializedName("leftParameterText")
    val leftParameterText: LeftParameterText,

    @SerializedName("leftParameterImage")
    val leftParameterImage: String,

    @SerializedName("rightParameter")
    val rightParameter: RightParameter,

    @SerializedName("rightParameterText")
    val rightParameterText: RightParameterText,

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
