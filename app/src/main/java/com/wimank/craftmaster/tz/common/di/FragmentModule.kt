package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.app.categories_screen.di.CategoriesFragmentScope
import com.wimank.craftmaster.tz.app.categories_screen.di.CategoriesModule
import com.wimank.craftmaster.tz.app.categories_screen.ui.CategoriesFragment
import com.wimank.craftmaster.tz.app.main_screen.di.MainGroupFragmentScope
import com.wimank.craftmaster.tz.app.main_screen.di.MainGroupModule
import com.wimank.craftmaster.tz.app.main_screen.ui.MainGroupFragment
import com.wimank.craftmaster.tz.app.recipe_screen.di.RecipeFragmentScope
import com.wimank.craftmaster.tz.app.recipe_screen.di.RecipeModule
import com.wimank.craftmaster.tz.app.recipe_screen.ui.RecipeFragment
import com.wimank.craftmaster.tz.app.recipes_list.di.RecipesListFragmentScope
import com.wimank.craftmaster.tz.app.recipes_list.di.RecipesListModule
import com.wimank.craftmaster.tz.app.recipes_list.ui.RecipesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @MainGroupFragmentScope
    @ContributesAndroidInjector(modules = [MainGroupModule::class])
    fun contributesMainGroupFragment(): MainGroupFragment

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
