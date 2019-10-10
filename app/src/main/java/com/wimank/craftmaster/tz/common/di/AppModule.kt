package com.wimank.craftmaster.tz.common.di

import android.content.Context
import com.wimank.craftmaster.tz.common.utils.NetManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideNetManager(context: Context) = NetManager(context)

}
