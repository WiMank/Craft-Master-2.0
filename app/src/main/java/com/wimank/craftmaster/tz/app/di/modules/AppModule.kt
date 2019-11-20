package com.wimank.craftmaster.tz.app.di.modules

import android.content.Context
import com.wimank.craftmaster.tz.app.mvp.models.LocaleManager
import com.wimank.craftmaster.tz.app.mvp.models.NetManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideNetManager(context: Context) = NetManager(context)

    @Singleton
    @Provides
    fun provideLocaleManager(context: Context) = LocaleManager(context)

}
