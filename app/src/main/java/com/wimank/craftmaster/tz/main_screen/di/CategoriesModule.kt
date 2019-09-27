package com.wimank.craftmaster.tz.main_screen.di

import android.content.Context
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.utils.NetManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.CategoriesManager
import com.wimank.craftmaster.tz.main_screen.mvp.presenters.CategoriesPresenter
import com.wimank.craftmaster.tz.main_screen.rest.CategoriesApi
import dagger.Module
import dagger.Provides

@Module
class CategoriesModule {

    @MainScreenScope
    @Provides
    fun provideCategoriesPresenter(
        context: Context,
        categoriesManager: CategoriesManager,
        netManager: NetManager
    ): CategoriesPresenter {
        return CategoriesPresenter(context, categoriesManager, netManager)
    }

    @MainScreenScope
    @Provides
    fun provideCategoriesManager(
        categoriesApi: CategoriesApi,
        craftMasterDataBase: CraftMasterDataBase
    ): CategoriesManager {
        return CategoriesManager(categoriesApi, craftMasterDataBase)
    }
}