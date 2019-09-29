package com.wimank.craftmaster.tz.main_screen.di

import android.content.Context
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.utils.NetManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupManager
import com.wimank.craftmaster.tz.main_screen.mvp.presenters.MainPresenter
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import dagger.Module
import dagger.Provides

@Module
class MainGroupModule {

    @MainScreenScope
    @Provides
    fun provideMainPresenter(
        mainGroupManager: MainGroupManager,
        netManager: NetManager
    ): MainPresenter {
        return MainPresenter(mainGroupManager, netManager)
    }

    @MainScreenScope
    @Provides
    fun provideMainGroupModel(
        context: Context,
        mainGroupApi: MainGroupApi,
        craftMasterDataBase: CraftMasterDataBase
    ): MainGroupManager {
        return MainGroupManager(context, mainGroupApi, craftMasterDataBase)
    }
}