package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.categories_screen.di.CategoriesModule
import com.wimank.craftmaster.tz.categories_screen.di.CategoriesScreenScope
import com.wimank.craftmaster.tz.categories_screen.ui.CategoriesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @CategoriesScreenScope
    @ContributesAndroidInjector(modules = [CategoriesModule::class])
    fun contributesCategoriesFragment(): CategoriesFragment

}

