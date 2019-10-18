package com.wimank.craftmaster.tz.app.di.modules

import com.wimank.craftmaster.tz.app.di.scopes.CategoriesFragmentScope
import com.wimank.craftmaster.tz.app.mvp.models.CategoriesManager
import com.wimank.craftmaster.tz.app.mvp.presenters.CategoriesPresenter
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
