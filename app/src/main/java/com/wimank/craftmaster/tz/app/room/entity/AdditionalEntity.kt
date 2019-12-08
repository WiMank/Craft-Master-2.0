package com.wimank.craftmaster.tz.app.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType

@Entity(
    tableName = "additional_info",
    primaryKeys = ["recipeAttr", "recipeImageName"],
    foreignKeys = [ForeignKey(
        entity = DescriptionEntity::class,
        parentColumns = ["recipeAttr", "recipeImageName"],
        childColumns = ["recipeAttr", "recipeImageName"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class AdditionalEntity(

    @SerializedName("recipeAttr")
    val recipeAttr: String,

    @SerializedName("recipeImageName")
    val recipeImageName: String,

    @SerializedName("attack_damage")
    val attackDamage: String,

    @SerializedName("durability")
    val durability: String,

    @SerializedName("armor")
    val armor: String,

    @SerializedName("restores")
    val restores: String,

    @SerializedName("leftpr")
    val leftPr: LocalizedType,

    @SerializedName("rightpr")
    val rightPr: LocalizedType,

    @SerializedName("small_image")
    val smallImage: String,

    @SerializedName("vers")
    val vers: Int

) : BaseEntity {
    override fun getVersion() = vers

    override fun getImage() = recipeImageName

}
