package com.wimank.craftmaster.tz.app.room

import androidx.room.Embedded
import androidx.room.Relation
import com.wimank.craftmaster.tz.app.room.entity.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entity.FavoritesEntity

data class DescWithFavorites(

    @Embedded
    val favoritesEntity: FavoritesEntity,

    @Relation(
        parentColumn = "fRecipeAttr",
        entityColumn = "recipeAttr"
    )
    val descriptionEntity: DescriptionEntity

)
