package com.wimank.craftmaster.tz.app.recipe_screen.di

import android.content.Context
import com.wimank.craftmaster.tz.app.recipe_screen.mvp.models.RecipeManager
import com.wimank.craftmaster.tz.app.recipe_screen.mvp.presenters.RecipePresenter
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
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
        context: Context,
        craftMasterDataBase: CraftMasterDataBase
    ): RecipeManager {
        return RecipeManager(context, craftMasterDataBase)
    }
}
