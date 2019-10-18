package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.room.entitys.CategoryEntity
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import io.reactivex.Single

class CategoriesManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun getCategoriesByGroupName(group: String): Single<List<CategoryEntity>> {
        return craftMasterDataBase.categoryDao().getCategoriesByGroupName(group)
    }
}
