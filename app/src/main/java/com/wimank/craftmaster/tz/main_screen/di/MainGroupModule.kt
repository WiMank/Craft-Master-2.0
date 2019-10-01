package com.wimank.craftmaster.tz.main_screen.di

import android.content.Context
import com.wimank.craftmaster.tz.common.rest.ImageApi
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.utils.NetManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.CategoriesManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupManager
import com.wimank.craftmaster.tz.main_screen.mvp.presenters.MainPresenter
import com.wimank.craftmaster.tz.main_screen.rest.CategoriesApi
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import dagger.Module
import dagger.Provides

@Module
class MainGroupModule {

    @MainScreenScope
    @Provides
    fun provideMainPresenter(
        mainGroupManager: MainGroupManager,
        categoriesManager: CategoriesManager,
        netManager: NetManager
    ): MainPresenter {
        return MainPresenter(mainGroupManager, categoriesManager, netManager)
    }

    @MainScreenScope
    @Provides
    fun provideMainGroupModel(
        context: Context,
        mainGroupApi: MainGroupApi,
        imageApi: ImageApi,
        craftMasterDataBase: CraftMasterDataBase
    ): MainGroupManager {
        return MainGroupManager(context, mainGroupApi, imageApi, craftMasterDataBase)
    }

    @MainScreenScope
    @Provides
    fun provideCategoriesManager(
        context: Context,
        categoriesApi: CategoriesApi,
        imageApi: ImageApi,
        craftMasterDataBase: CraftMasterDataBase
    ): CategoriesManager {
        return CategoriesManager(context, categoriesApi, imageApi, craftMasterDataBase)
    }
}