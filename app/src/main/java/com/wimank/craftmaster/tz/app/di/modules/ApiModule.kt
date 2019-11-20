package com.wimank.craftmaster.tz.app.di.modules

import com.wimank.craftmaster.tz.app.rest.api.*
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

    @Singleton
    @Provides
    fun provideDevicesApi(retrofit: Retrofit): DevicesApi {
        return retrofit.create(DevicesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAchievementsApi(retrofit: Retrofit): AchievementsApi {
        return retrofit.create(AchievementsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBiomesApi(retrofit: Retrofit): BiomesApi {
        return retrofit.create(BiomesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBrewingApi(retrofit: Retrofit): BrewingApi {
        return retrofit.create(BrewingApi::class.java)
    }
}
