package com.wimank.craftmaster.tz.app.di.modules

import android.content.Context
import com.wimank.craftmaster.tz.app.di.scopes.MainActivityScope
import com.wimank.craftmaster.tz.app.mvp.models.DataManager
import com.wimank.craftmaster.tz.app.mvp.models.LocaleManager
import com.wimank.craftmaster.tz.app.mvp.models.NetManager
import com.wimank.craftmaster.tz.app.mvp.presenters.MainActivityPresenter
import com.wimank.craftmaster.tz.app.rest.api.*
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.utils.ImageUtils
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @MainActivityScope
    @Provides
    fun provideMainPresenter(
        dataManager: DataManager,
        netManager: NetManager
    ): MainActivityPresenter {
        return MainActivityPresenter(
            dataManager,
            netManager
        )
    }

    @MainActivityScope
    @Provides
    fun provideDataManager(
        imageUtils: ImageUtils,
        imageApi: ImageApi,
        recipesApi: RecipesApi,
        mobsApi: MobsApi,
        devicesApi: DevicesApi,
        achievementsApi: AchievementsApi,
        biomesApi: BiomesApi,
        brewingApi: BrewingApi,
        localeManager: LocaleManager,
        craftMasterDataBase: CraftMasterDataBase
    ): DataManager {
        return DataManager(
            imageUtils,
            imageApi,
            recipesApi,
            mobsApi,
            devicesApi,
            achievementsApi,
            biomesApi,
            brewingApi,
            localeManager,
            craftMasterDataBase
        )
    }

    @MainActivityScope
    @Provides
    fun provideImageUtils(context: Context) = ImageUtils(context)

}
