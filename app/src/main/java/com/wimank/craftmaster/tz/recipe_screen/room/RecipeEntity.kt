package com.wimank.craftmaster.tz.recipe_screen.room

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.room.BaseEntity

@Entity(tableName = "craft_recipes", primaryKeys = ["recipeAttr", "recipeImageName"])
data class RecipeEntity(

    @SerializedName("recipeImageName")
    val recipeImageName: String,

    @SerializedName("recipeAttr")
    val recipeAttr: String,

    @SerializedName("group")
    val group: String,

    @SerializedName("firstSlot")
    val firstSlot: String,

    @SerializedName("secondSlot")
    val secondSlot: String,

    @SerializedName("threeSlot")
    val threeSlot: String,

    @SerializedName("fourthSlot")
    val fourthSlot: String,

    @SerializedName("fifthSlot")
    val fifthSlot: String,

    @SerializedName("sixthSlot")
    val sixthSlot: String,

    @SerializedName("seventhSlot")
    val seventhSlot: String,

    @SerializedName("eighthSlot")
    val eighthSlot: String,

    @SerializedName("ninthSlot")
    val ninthSlot: String,

    @SerializedName("vers")
    val vers: Int
) : BaseEntity {
    override fun getVersion() = vers
    override fun getImage() = recipeImageName
}
