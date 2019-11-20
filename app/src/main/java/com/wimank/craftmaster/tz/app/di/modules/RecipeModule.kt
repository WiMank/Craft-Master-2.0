package com.wimank.craftmaster.tz.app.di.modules

import com.wimank.craftmaster.tz.app.di.scopes.RecipeFragmentScope
import com.wimank.craftmaster.tz.app.mvp.models.LocaleManager
import com.wimank.craftmaster.tz.app.mvp.models.RecipeManager
import com.wimank.craftmaster.tz.app.mvp.presenters.RecipePresenter
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import dagger.Module
import dagger.Provides

@Module
class RecipeModule {

    @RecipeFragmentScope
    @Provides
    fun provideRecipePresenter(recipeManager: RecipeManager): RecipePresenter {
        return RecipePresenter(recipeManager)
    }

    @RecipeFragmentScope
    @Provides
    fun provideRecipeManager(
        localeManager: LocaleManager,
        craftMasterDataBase: CraftMasterDataBase
    ): RecipeManager {
        return RecipeManager(localeManager, craftMasterDataBase)
    }
}
