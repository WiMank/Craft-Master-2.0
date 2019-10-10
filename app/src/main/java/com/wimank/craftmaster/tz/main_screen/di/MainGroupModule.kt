package com.wimank.craftmaster.tz.main_screen.di

import android.content.Context
import com.wimank.craftmaster.tz.categories_screen.rest.CategoriesApi
import com.wimank.craftmaster.tz.common.di.MainFragmentScope
import com.wimank.craftmaster.tz.common.rest.ImageApi
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.utils.NetManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.DataManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupManager
import com.wimank.craftmaster.tz.main_screen.mvp.presenters.MainPresenter
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.recipe_screen.rest.RecipesApi
import dagger.Module
import dagger.Provides

@Module
class MainGroupModule {

    @MainFragmentScope
    @Provides
    fun provideMainPresenter(
        dataManager: DataManager,
        netManager: NetManager
    ): MainPresenter {
        return MainPresenter(dataManager, netManager)
    }

    @MainFragmentScope
    @Provides
    fun provideMainGroupManager(
        context: Context,
        mainGroupApi: MainGroupApi,
        imageApi: ImageApi,
        craftMasterDataBase: CraftMasterDataBase
    ): MainGroupManager {
        return MainGroupManager(context, mainGroupApi, imageApi, craftMasterDataBase)
    }

    @MainFragmentScope
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
