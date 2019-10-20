package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.app.rest.api.CategoriesApi
import com.wimank.craftmaster.tz.app.rest.api.MobsApi
import com.wimank.craftmaster.tz.app.rest.api.RecipesApi
import com.wimank.craftmaster.tz.common.rest.ImageApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideCategoriesApi(retrofit: Retrofit): CategoriesApi {
        return retrofit.create(CategoriesApi::class.java)
    }

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
