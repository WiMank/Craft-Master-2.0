package com.wimank.craftmaster.tz.categories_screen.di

import com.wimank.craftmaster.tz.categories_screen.mvp.models.CategoriesManager
import com.wimank.craftmaster.tz.categories_screen.mvp.presenters.CategoriesPresenter
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import dagger.Module
import dagger.Provides

@Module
class CategoriesModule {

    @CategoriesScreenScope
    @Provides
    fun provideCategoriesPresenter(categoriesManager: CategoriesManager): CategoriesPresenter {
        return CategoriesPresenter(categoriesManager)
    }

    @CategoriesScreenScope
    @Provides
    fun provideCategoriesManager(craftMasterDataBase: CraftMasterDataBase): CategoriesManager {
        return CategoriesManager(craftMasterDataBase)
    }
}