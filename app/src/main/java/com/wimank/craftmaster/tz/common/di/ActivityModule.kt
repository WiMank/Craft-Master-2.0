package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.main_screen.ui.MainActivity
import com.wimank.craftmaster.tz.main_screen.di.MainActModule
import com.wimank.craftmaster.tz.main_screen.di.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainActModule::class])
    fun contributeMainActivity(): MainActivity

}