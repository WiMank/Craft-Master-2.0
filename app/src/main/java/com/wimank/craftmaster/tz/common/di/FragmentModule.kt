package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.app.di.modules.CategoriesModule
import com.wimank.craftmaster.tz.app.di.modules.RecipeModule
import com.wimank.craftmaster.tz.app.di.modules.RecipesListModule
import com.wimank.craftmaster.tz.app.di.scopes.CategoriesFragmentScope
import com.wimank.craftmaster.tz.app.di.scopes.RecipeFragmentScope
import com.wimank.craftmaster.tz.app.di.scopes.RecipesListFragmentScope
import com.wimank.craftmaster.tz.app.ui.CategoriesFragment
import com.wimank.craftmaster.tz.app.ui.RecipeFragment
import com.wimank.craftmaster.tz.app.ui.RecipesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @CategoriesFragmentScope
    @ContributesAndroidInjector(modules = [CategoriesModule::class])
    fun contributesCategoriesFragment(): CategoriesFragment

    @RecipesListFragmentScope
    @ContributesAndroidInjector(modules = [RecipesListModule::class])
    fun contributesRecipesListFragment(): RecipesListFragment

    @RecipeFragmentScope
    @ContributesAndroidInjector(modules = [RecipeModule::class])
    fun contributesRecipesFragment(): RecipeFragment

}
