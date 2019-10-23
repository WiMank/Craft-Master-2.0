package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entitys.MobsEntity
import io.reactivex.Single

class RecipesListManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun getRecipesList(modification: String): Single<List<DescriptionEntity>> {
        return craftMasterDataBase.descriptionDao().getRecipesListFromDb(modification)
    }

    fun getMobsList(): Single<List<MobsEntity>> {
        return craftMasterDataBase.mobsDao().getMobs()
    }
}