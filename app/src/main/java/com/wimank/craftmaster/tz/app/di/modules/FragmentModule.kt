package com.wimank.craftmaster.tz.app.di.modules

import com.wimank.craftmaster.tz.app.di.scopes.MobsFragmentScope
import com.wimank.craftmaster.tz.app.di.scopes.RecipeFragmentScope
import com.wimank.craftmaster.tz.app.di.scopes.RecipesListFragmentScope
import com.wimank.craftmaster.tz.app.ui.MobFragment
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

    @MobsFragmentScope
    @ContributesAndroidInjector(modules = [MobsModule::class])
    fun contributesMobsFragment(): MobFragment
}
