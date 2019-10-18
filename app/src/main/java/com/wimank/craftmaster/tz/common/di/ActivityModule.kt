package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.app.di.scopes.MainActivityScope
import com.wimank.craftmaster.tz.app.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @MainActivityScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    fun contributeMainActivity(): MainActivity

}
