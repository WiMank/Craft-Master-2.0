package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.main_screen.ui.MainActivity
import com.wimank.craftmaster.tz.main_screen.di.MainActModule
import com.wimank.craftmaster.tz.main_screen.di.MainScreenScope
import com.wimank.craftmaster.tz.main_screen.di.CategoriesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @MainScreenScope
    @ContributesAndroidInjector(modules = [MainActModule::class, CategoriesModule::class])
    fun contributeMainActivity(): MainActivity

}