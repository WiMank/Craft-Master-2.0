package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.categories_screen.di.CategoriesModule
import com.wimank.craftmaster.tz.categories_screen.di.CategoriesScreenScope
import com.wimank.craftmaster.tz.categories_screen.ui.CategoriesFragment
import com.wimank.craftmaster.tz.main_screen.di.MainGroupModule
import com.wimank.craftmaster.tz.main_screen.di.MainScreenScope
import com.wimank.craftmaster.tz.main_screen.ui.MainGroupFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @CategoriesScreenScope
    @ContributesAndroidInjector(modules = [CategoriesModule::class])
    fun contributesCategoriesFragment(): CategoriesFragment

    @MainScreenScope
    @ContributesAndroidInjector(modules = [MainGroupModule::class])
    fun contributesMainGroupFragment(): MainGroupFragment

}

