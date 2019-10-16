package com.wimank.craftmaster.tz.app.recipes_list.di

import com.wimank.craftmaster.tz.app.recipes_list.mvp.models.RecipesListManager
import com.wimank.craftmaster.tz.app.recipes_list.mvp.presenter.RecipesListPresenter
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import dagger.Module
import dagger.Provides

@Module
class RecipesListModule {

    @RecipesListFragmentScope
    @Provides
    fun provideRecipesListPresenter(recipesListManager: RecipesListManager): RecipesListPresenter {
        return RecipesListPresenter(recipesListManager)
    }

    @RecipesListFragmentScope
    @Provides
    fun provideRecipesListManager(craftMasterDataBase: CraftMasterDataBase): RecipesListManager {
        return RecipesListManager(craftMasterDataBase)
    }
}
