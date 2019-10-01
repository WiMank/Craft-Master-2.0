package com.wimank.craftmaster.tz.main_screen.di

import com.wimank.craftmaster.tz.main_screen.mvp.presenters.CategoriesPresenter
import dagger.Module
import dagger.Provides

@Module
class CategoriesModule {

    @MainScreenScope
    @Provides
    fun provideCategoriesPresenter(
    ): CategoriesPresenter {
        return CategoriesPresenter()
    }
}