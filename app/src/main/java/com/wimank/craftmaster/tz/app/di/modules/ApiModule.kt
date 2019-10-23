package com.wimank.craftmaster.tz.app.di.modules

import com.wimank.craftmaster.tz.app.rest.api.ImageApi
import com.wimank.craftmaster.tz.app.rest.api.MobsApi
import com.wimank.craftmaster.tz.app.rest.api.RecipesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideImageApi(retrofit: Retrofit): ImageApi {
        return retrofit.create(ImageApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRecipeApi(retrofit: Retrofit): RecipesApi {
        return retrofit.create(RecipesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMobsApi(retrofit: Retrofit): MobsApi {
        return retrofit.create(MobsApi::class.java)
    }
}