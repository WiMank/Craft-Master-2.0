package com.wimank.craftmaster.tz.main_screen.di

import android.content.Context
import com.wimank.craftmaster.tz.main_screen.mvp.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActModule {

    @MainScope
    @Provides
    fun provideMainPresenter(context: Context) = MainPresenter(context)


}