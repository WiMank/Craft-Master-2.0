package com.wimank.craftmaster.tz.recipe_screen.room.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.room.entities.BaseEntity


@Entity(tableName = "mc_craft_recipe", primaryKeys = ["recipeAttr", "recipeImageName"])
data class McRecipeEntity(

    @SerializedName("recipeImageName")
    var recipeImageName: String,

    @SerializedName("recipeAttr")
    var recipeAttr: String,

    @SerializedName("firstSlot")
    var firstSlot: String,

    @SerializedName("secondSlot")
    var secondSlot: String,

    @SerializedName("threeSlot")
    var threeSlot: String,

    @SerializedName("fourthSlot")
    var fourthSlot: String,

    @SerializedName("fifthSlot")
    var fifthSlot: String,

    @SerializedName("sixthSlot")
    var sixthSlot: String,

    @SerializedName("seventhSlot")
    var seventhSlot: String,

    @SerializedName("eighthSlot")
    var eighthSlot: String,

    @SerializedName("ninthSlot")
    var ninthSlot: String,

    @SerializedName("vers")
    var vers: Int
) : BaseEntity {
    override fun getVersion() = vers
    override fun getImage() = recipeImageName
}