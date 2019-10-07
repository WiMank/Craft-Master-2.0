package com.wimank.craftmaster.tz.categories_screen.mvp.models

import com.wimank.craftmaster.tz.categories_screen.room.CategoryEntity
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import io.reactivex.Single

class CategoriesManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun getMcCategories(): Single<List<CategoryEntity>> {
        return craftMasterDataBase.mcCategoryDao().getCategoriesFromDb()
    }

}