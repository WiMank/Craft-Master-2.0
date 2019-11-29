package com.wimank.craftmaster.tz.app.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "craft_recipes",
    primaryKeys = ["recipeAttr", "recipeImageName"],
    foreignKeys = [
        ForeignKey(
            entity = DescriptionEntity::class,
            parentColumns = ["recipeAttr", "recipeImageName"],
            childColumns = ["recipeAttr", "recipeImageName"],
            onDelete = CASCADE
        )]
)
data class RecipeEntity(

    @SerializedName("recipeAttr")
    val recipeAttr: String,

    @SerializedName("recipeImageName")
    val recipeImageName: String,

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
