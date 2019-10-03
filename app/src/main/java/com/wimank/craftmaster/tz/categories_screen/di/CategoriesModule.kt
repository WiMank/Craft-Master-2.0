package com.wimank.craftmaster.tz.categories_screen.di

import com.wimank.craftmaster.tz.categories_screen.mvp.presenters.CategoriesPresenter
import dagger.Module
import dagger.Provides

@Module
class CategoriesModule {

    @CategoriesScreenScope
    @Provides
    fun provideCategoriesPresenter(
    ): CategoriesPresenter {
        return CategoriesPresenter()
    }
}