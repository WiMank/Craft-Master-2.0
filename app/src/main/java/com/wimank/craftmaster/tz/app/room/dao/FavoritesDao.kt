package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entity.FavoriteEntity
import io.reactivex.Single

@Dao
interface FavoritesDao : BaseDao<FavoriteEntity> {

    @Query("SELECT * FROM favorites")
    fun getFavoritesList(): Single<List<FavoriteEntity>>

    @Query("SELECT * FROM favorites WHERE fRecipeAttr =:recipeAttr")
    fun checkFavorite(recipeAttr: String): FavoriteEntity?

}
