package com.wimank.craftmaster.tz.app.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "favorites",
    primaryKeys = ["recipeAttr", "recipeImageName"],
    foreignKeys = [ForeignKey(
        entity = DescriptionEntity::class,
        parentColumns = ["recipeAttr", "recipeImageName"],
        childColumns = ["recipeAttr", "recipeImageName"],
        onDelete = CASCADE
    )]
)
data class FavoritesEntity(

    val recipeAttr: String,

    val recipeImageName: String,

    val favorite: Boolean

)
