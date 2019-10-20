package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.app.di.modules.MainActivityModule
import com.wimank.craftmaster.tz.app.di.scopes.MainActivityScope
import com.wimank.craftmaster.tz.app.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @MainActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class, FragmentModule::class])
    fun contributeMainActivity(): MainActivity

}
