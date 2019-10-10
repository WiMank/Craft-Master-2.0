package com.wimank.craftmaster.tz.app.main_screen.di

import android.content.Context
import com.wimank.craftmaster.tz.app.categories_screen.rest.CategoriesApi
import com.wimank.craftmaster.tz.app.main_screen.mvp.models.DataManager
import com.wimank.craftmaster.tz.app.main_screen.mvp.models.MainGroupManager
import com.wimank.craftmaster.tz.app.main_screen.mvp.presenters.MainPresenter
import com.wimank.craftmaster.tz.app.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.app.recipe_screen.rest.RecipesApi
import com.wimank.craftmaster.tz.common.rest.ImageApi
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
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
    ): MainPresenter {
        return MainPresenter(dataManager, netManager)
    }

    @MainGroupFragmentScope
    @Provides
    fun provideMainGroupManager(
        context: Context,
        mainGroupApi: MainGroupApi,
        imageApi: ImageApi,
        craftMasterDataBase: CraftMasterDataBase
    ): MainGroupManager {
        return MainGroupManager(context, mainGroupApi, imageApi, craftMasterDataBase)
    }

    @MainGroupFragmentScope
    @Provides
    fun provideDataManager(
        context: Context,
        categoriesApi: CategoriesApi,
        mainGroupApi: MainGroupApi,
        imageApi: ImageApi,
        recipesApi: RecipesApi,
        craftMasterDataBase: CraftMasterDataBase
    ): DataManager {
        return DataManager(
            context,
            categoriesApi,
            mainGroupApi,
            imageApi,
            recipesApi,
            craftMasterDataBase
        )
    }
}
