package com.wimank.craftmaster.tz.app.di.modules

import com.wimank.craftmaster.tz.app.di.scopes.*
import com.wimank.craftmaster.tz.app.ui.*
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

    @AchievementsScope
    @ContributesAndroidInjector(modules = [AchievementModule::class])
    fun contributesAchievementsFragment(): AchievementsFragment

    @BiomesScope
    @ContributesAndroidInjector(modules = [BiomesModule::class])
    fun contributesBiomeFragment(): BiomeFragment

    @SectionScope
    @ContributesAndroidInjector
    fun contributesSectionFragment(): SectionFragment

}
