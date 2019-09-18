package com.wimank.craftmaster.tz.main_screen.di

import android.content.Context
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupDataBaseManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupManager
import com.wimank.craftmaster.tz.main_screen.mvp.presenters.MainPresenter
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import dagger.Module
import dagger.Provides

@Module
class MainActModule {

    @MainScreenScope
    @Provides
    fun provideMainPresenter(mainGroupManager: MainGroupManager) = MainPresenter(mainGroupManager)

    @MainScreenScope
    @Provides
    fun provideMainGroupModel(
        context: Context,
        mainGroupApi: MainGroupApi,
        mainGroupDataBaseManager: MainGroupDataBaseManager
    ): MainGroupManager {
        return MainGroupManager(context, mainGroupApi, mainGroupDataBaseManager)
    }

    @MainScreenScope
    @Provides
    fun provideMainGroupDataBaseManager(craftMasterDataBase: CraftMasterDataBase): MainGroupDataBaseManager {
        return MainGroupDataBaseManager(craftMasterDataBase)
    }
}