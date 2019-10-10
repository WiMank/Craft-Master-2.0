package com.wimank.craftmaster.tz.recipes_list.mvp.models

import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.recipes_list.room.RecipesListItem
import io.reactivex.Single

class RecipesListManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun getRecipesList(group: String): Single<List<RecipesListItem>> {
        return craftMasterDataBase.descriptionDao().getRecipesListFromDb(group)
    }
}
