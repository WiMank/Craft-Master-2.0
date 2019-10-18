package com.wimank.craftmaster.tz.app.di.modules

import com.wimank.craftmaster.tz.app.di.scopes.RecipesListFragmentScope
import com.wimank.craftmaster.tz.app.mvp.models.RecipesListManager
import com.wimank.craftmaster.tz.app.mvp.presenters.RecipesListPresenter
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
