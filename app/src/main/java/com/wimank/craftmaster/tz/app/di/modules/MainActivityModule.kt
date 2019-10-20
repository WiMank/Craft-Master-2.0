package com.wimank.craftmaster.tz.app.di.modules

import android.content.Context
import com.wimank.craftmaster.tz.app.di.scopes.MainActivityScope
import com.wimank.craftmaster.tz.app.mvp.models.DataManager
import com.wimank.craftmaster.tz.app.mvp.presenters.MainActivityPresenter
import com.wimank.craftmaster.tz.app.rest.api.CategoriesApi
import com.wimank.craftmaster.tz.app.rest.api.RecipesApi
import com.wimank.craftmaster.tz.common.rest.ImageApi
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.utils.ImageUtils
import com.wimank.craftmaster.tz.common.utils.NetManager
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
        categoriesApi: CategoriesApi,
        imageApi: ImageApi,
        recipesApi: RecipesApi,
        craftMasterDataBase: CraftMasterDataBase
    ): DataManager {
        return DataManager(
            imageUtils,
            categoriesApi,
            imageApi,
            recipesApi,
            craftMasterDataBase
        )
    }

    @MainActivityScope
    @Provides
    fun provideImageUtils(context: Context) = ImageUtils(context)

}
