package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.room.entity.FavoriteEntity
import io.reactivex.Single

class FavoriteManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun getFavoritesList(): Single<List<FavoriteEntity>> {
        return craftMasterDataBase.favoritesDao().getFavoritesList()
    }

}
