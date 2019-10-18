package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.room.RecipesListItem
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import io.reactivex.Single

class RecipesListManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun getRecipesList(group: String): Single<List<RecipesListItem>> {
        return craftMasterDataBase.descriptionDao().getRecipesListFromDb(group)
    }
}
