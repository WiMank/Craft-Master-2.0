package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.room.entity.BiomesEntity
import com.wimank.craftmaster.tz.app.room.entity.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entity.FavoriteEntity
import com.wimank.craftmaster.tz.app.room.entity.MobsEntity
import io.reactivex.Single

class RecipesListManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun getRecipesList(modification: String): Single<List<DescriptionEntity>> {
        return craftMasterDataBase.descriptionDao().getRecipesListFromDb(modification)
    }

    fun getMobsList(): Single<List<MobsEntity>> {
        return craftMasterDataBase.mobsDao().getMobs()
    }

    fun getBiomesList(): Single<List<BiomesEntity>> {
        return craftMasterDataBase.biomesDao().getBiomes()
    }

    fun getFavoritesList(): Single<List<FavoriteEntity>> {
        return craftMasterDataBase.favoritesDao().getFavoritesList()
    }
}
