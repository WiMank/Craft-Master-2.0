package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideMainGroupApi(retrofit: Retrofit): MainGroupApi {
        return retrofit.create(MainGroupApi::class.java)
    }

}