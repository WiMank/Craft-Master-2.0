package com.wimank.craftmaster.tz.main_screen.mvp.models

import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.room.entities.CategoriesEntity
import com.wimank.craftmaster.tz.main_screen.rest.CategoriesApi
import com.wimank.craftmaster.tz.main_screen.rest.response.CategoriesResponse
import io.reactivex.Single

class CategoriesManager(
    private val mCategoriesApi: CategoriesApi,
    private val mCraftMasterDataBase: CraftMasterDataBase
) {

    fun getCategories(url: String): Single<CategoriesResponse> {
        return mCategoriesApi.getCategories(url)
    }

    fun getCategoriesFromDb(category: String): Single<List<CategoriesEntity>> {
        return mCraftMasterDataBase.categoriesDao().getCategoriesFromDb(category)
    }

}