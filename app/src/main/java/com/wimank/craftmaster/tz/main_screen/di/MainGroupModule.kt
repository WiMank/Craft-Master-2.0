package com.wimank.craftmaster.tz.main_screen.di

import android.content.Context
import com.wimank.craftmaster.tz.common.rest.ImageApi
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.utils.NetManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.DataManager
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
        dataManager: DataManager,
        netManager: NetManager
    ): MainPresenter {
        return MainPresenter(dataManager, netManager)
    }

    @MainScreenScope
    @Provides
    fun provideMainGroupManager(
        context: Context,
        mainGroupApi: MainGroupApi,
        imageApi: ImageApi,
        craftMasterDataBase: CraftMasterDataBase
    ): MainGroupManager {
        return MainGroupManager(context, mainGroupApi, imageApi, craftMasterDataBase)
    }

    @MainScreenScope
    @Provides
    fun provideDataManager(
        context: Context,
        categoriesApi: CategoriesApi,
        mainGroupApi: MainGroupApi,
        imageApi: ImageApi,
        craftMasterDataBase: CraftMasterDataBase
    ): DataManager {
        return DataManager(context, categoriesApi, mainGroupApi, imageApi, craftMasterDataBase)
    }
}