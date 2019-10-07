package com.wimank.craftmaster.tz.categories_screen.mvp.models

import com.wimank.craftmaster.tz.categories_screen.room.CategoryEntity
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import io.reactivex.Single

class CategoriesManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun getMcCategoriesByGroupName(group: String): Single<List<CategoryEntity>> {
        return craftMasterDataBase.categoryDao().getMcCategoriesByGroupName(group)
    }
}