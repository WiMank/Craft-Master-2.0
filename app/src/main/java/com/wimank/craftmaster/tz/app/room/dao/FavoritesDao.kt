package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entity.FavoritesEntity
import io.reactivex.Single

@Dao
interface FavoritesDao : BaseDao<FavoritesEntity> {

    @Query("SELECT * FROM favorites")
    fun getFavoritesList(): Single<List<FavoritesEntity>>

}
