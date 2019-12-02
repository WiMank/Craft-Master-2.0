package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.room.entity.DescriptionEntity
import io.reactivex.Single

class SearchManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun getAllRecipes(): Single<List<DescriptionEntity>> {
        return craftMasterDataBase.descriptionDao().getAllRecipesListFromDb()
    }

}
