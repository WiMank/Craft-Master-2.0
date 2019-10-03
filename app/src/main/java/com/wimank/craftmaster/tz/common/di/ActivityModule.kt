package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.main_screen.di.MainGroupModule
import com.wimank.craftmaster.tz.main_screen.di.MainScreenScope
import com.wimank.craftmaster.tz.main_screen.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @MainScreenScope
    @ContributesAndroidInjector(modules = [MainGroupModule::class])
    fun contributeMainActivity(): MainActivity

}