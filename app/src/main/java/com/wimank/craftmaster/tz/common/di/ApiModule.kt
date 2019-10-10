package com.wimank.craftmaster.tz.common.di

import com.wimank.craftmaster.tz.app.categories_screen.rest.CategoriesApi
import com.wimank.craftmaster.tz.app.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.app.recipe_screen.rest.RecipesApi
import com.wimank.craftmaster.tz.common.rest.ImageApi
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
}
