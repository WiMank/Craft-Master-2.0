package com.wimank.craftmaster.tz.app.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType

@Entity(
    tableName = "favorites",
    primaryKeys = ["fRecipeAttr", "recipeImageName"],
    foreignKeys = [ForeignKey(
        entity = DescriptionEntity::class,
        parentColumns = ["recipeAttr", "recipeImageName"],
        childColumns = ["fRecipeAttr", "recipeImageName"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class FavoriteEntity(

    val fRecipeAttr: String,

    val recipeName: LocalizedType,

    val recipeImageName: String

) : BaseEntity {

    override fun getVersion() = 0

    override fun getImage() = recipeImageName
}
