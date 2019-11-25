package com.wimank.craftmaster.tz.app.room.entity

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType

@Entity(tableName = "description_craft_recipes", primaryKeys = ["recipeName", "recipeImageName"])
data class DescriptionEntity(

    @SerializedName("recipeAttr")
    val recipeAttr: String,

    @SerializedName("recipeImageName")
    val recipeImageName: String,

    @SerializedName("modification")
    val modification: String,

    @SerializedName("recipe_name")
    val recipeName: LocalizedType,

    @SerializedName("leftParameter")
    val leftParameter: LocalizedType,

    @SerializedName("leftParameterText")
    val leftParameterText: LocalizedType,

    @SerializedName("leftParameterImage")
    val leftParameterImage: String,

    @SerializedName("rightParameter")
    val rightParameter: LocalizedType,

    @SerializedName("rightParameterText")
    val rightParameterText: LocalizedType,

    @SerializedName("descriptionCraft")
    val descriptionCraft: LocalizedType,

    @SerializedName("wikiLink")
    val wikiLink: String,

    @SerializedName("vers")
    val vers: Int,

    @Expose
    val favorite: Boolean = false

) : BaseEntity {
    override fun getVersion() = vers
    override fun getImage() = recipeImageName
}
