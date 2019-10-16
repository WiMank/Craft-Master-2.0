package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.app.main_screen.di.MainActivityScope
import com.wimank.craftmaster.tz.app.main_screen.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @MainActivityScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    fun contributeMainActivity(): MainActivity

}
