package com.wimank.craftmaster.tz.app.di.modules

import android.content.Context
import com.wimank.craftmaster.tz.app.di.scopes.MainGroupFragmentScope
import com.wimank.craftmaster.tz.app.mvp.models.DataManager
import com.wimank.craftmaster.tz.app.mvp.presenters.MainFragmentPresenter
import com.wimank.craftmaster.tz.app.rest.CategoriesApi
import com.wimank.craftmaster.tz.app.rest.MainGroupApi
import com.wimank.craftmaster.tz.app.rest.RecipesApi
import com.wimank.craftmaster.tz.common.rest.ImageApi
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.utils.ImageUtils
import com.wimank.craftmaster.tz.common.utils.NetManager
import dagger.Module
import dagger.Provides

@Module
class MainGroupModule {

    @MainGroupFragmentScope
    @Provides
    fun provideMainPresenter(
        dataManager: DataManager,
        netManager: NetManager
    ): MainFragmentPresenter {
        return MainFragmentPresenter(
            dataManager,
            netManager
        )
    }

    @MainGroupFragmentScope
    @Provides
    fun provideDataManager(
        imageUtils: ImageUtils,
        categoriesApi: CategoriesApi,
        mainGroupApi: MainGroupApi,
        imageApi: ImageApi,
        recipesApi: RecipesApi,
        craftMasterDataBase: CraftMasterDataBase
    ): DataManager {
        return DataManager(
            imageUtils,
            categoriesApi,
            mainGroupApi,
            imageApi,
            recipesApi,
            craftMasterDataBase
        )
    }

    @MainGroupFragmentScope
    @Provides
    fun provideImageUtils(context: Context) = ImageUtils(context)

}
