package com.wimank.craftmaster.tz.common.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityModule::class])
interface AppComponent : AndroidInjector<CraftMasterApp> {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }
}