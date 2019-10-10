package com.wimank.craftmaster.tz.recipes_list.di

import com.wimank.craftmaster.tz.common.di.RecipesListFragmentScope
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.recipes_list.mvp.models.RecipesListManager
import com.wimank.craftmaster.tz.recipes_list.mvp.presenter.RecipesListPresenter
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
