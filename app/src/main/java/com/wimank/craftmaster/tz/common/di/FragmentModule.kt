package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.app.di.modules.RecipeModule
import com.wimank.craftmaster.tz.app.di.modules.RecipesListModule
import com.wimank.craftmaster.tz.app.di.scopes.RecipeFragmentScope
import com.wimank.craftmaster.tz.app.di.scopes.RecipesListFragmentScope
import com.wimank.craftmaster.tz.app.ui.RecipeFragment
import com.wimank.craftmaster.tz.app.ui.RecipesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @RecipesListFragmentScope
    @ContributesAndroidInjector(modules = [RecipesListModule::class])
    fun contributesRecipesListFragment(): RecipesListFragment

    @RecipeFragmentScope
    @ContributesAndroidInjector(modules = [RecipeModule::class])
    fun contributesRecipesFragment(): RecipeFragment

}
