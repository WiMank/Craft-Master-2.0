package com.wimank.craftmaster.tz.common.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CraftMasterApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .build()
    }
}