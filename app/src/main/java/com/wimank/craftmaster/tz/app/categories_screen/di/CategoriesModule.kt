package com.wimank.craftmaster.tz.app.categories_screen.di

import com.wimank.craftmaster.tz.app.categories_screen.mvp.models.CategoriesManager
import com.wimank.craftmaster.tz.app.categories_screen.mvp.presenters.CategoriesPresenter
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import dagger.Module
import dagger.Provides

@Module
class CategoriesModule {

    @CategoriesFragmentScope
    @Provides
    fun provideCategoriesPresenter(categoriesManager: CategoriesManager): CategoriesPresenter {
        return CategoriesPresenter(categoriesManager)
    }

    @CategoriesFragmentScope
    @Provides
    fun provideCategoriesManager(craftMasterDataBase: CraftMasterDataBase): CategoriesManager {
        return CategoriesManager(craftMasterDataBase)
    }
}
