package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.mvp.common.SEARCH_VALUE
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.room.entity.BiomesEntity
import com.wimank.craftmaster.tz.app.room.entity.DescriptionEntity
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

    fun getAllRecipes(modification: String): Single<List<DescriptionEntity>> {
        return if (modification == SEARCH_VALUE)
            craftMasterDataBase.descriptionDao().getAllRecipesListFromDb()
        else
            craftMasterDataBase.descriptionDao().getRecipesListFromDb(modification)
    }

}
