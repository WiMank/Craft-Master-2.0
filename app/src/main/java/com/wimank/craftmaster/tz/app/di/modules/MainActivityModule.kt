package com.wimank.craftmaster.tz.app.di.modules

import android.content.Context
import com.wimank.craftmaster.tz.app.di.scopes.MainActivityScope
import com.wimank.craftmaster.tz.app.mvp.models.ApiManager
import com.wimank.craftmaster.tz.app.mvp.models.ItemsDataManager
import com.wimank.craftmaster.tz.app.mvp.models.LocaleManager
import com.wimank.craftmaster.tz.app.mvp.models.NetManager
import com.wimank.craftmaster.tz.app.mvp.presenters.MainActivityPresenter
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.utils.ImageUtils
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @MainActivityScope
    @Provides
    fun provideMainPresenter(
        itemsDataManager: ItemsDataManager,
        netManager: NetManager
    ): MainActivityPresenter {
        return MainActivityPresenter(
            itemsDataManager,
            netManager
        )
    }

    @MainActivityScope
    @Provides
    fun provideDataManager(
        apiManager: ApiManager,
        imageUtils: ImageUtils,
        localeManager: LocaleManager,
        craftMasterDataBase: CraftMasterDataBase
    ): ItemsDataManager {
        return ItemsDataManager(
            apiManager,
            imageUtils,
            localeManager,
            craftMasterDataBase
        )
    }

    @MainActivityScope
    @Provides
    fun provideImageUtils(context: Context) = ImageUtils(context)

}
