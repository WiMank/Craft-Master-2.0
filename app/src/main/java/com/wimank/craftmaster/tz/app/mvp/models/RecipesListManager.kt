package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.room.RecipesListItem
import io.reactivex.Single

class RecipesListManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun getRecipesList(modification: String): Single<List<RecipesListItem>> {
        return craftMasterDataBase.descriptionDao().getRecipesListFromDb()
    }
}
