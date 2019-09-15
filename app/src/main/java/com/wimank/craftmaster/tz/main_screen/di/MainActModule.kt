package com.wimank.craftmaster.tz.main_screen.di

import android.content.Context
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupModel
import com.wimank.craftmaster.tz.main_screen.mvp.presenters.MainPresenter
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import dagger.Module
import dagger.Provides

@Module
class MainActModule {

    @MainScreenScope
    @Provides
    fun provideMainPresenter(context: Context, mainGroupModel : MainGroupModel) = MainPresenter(context, mainGroupModel)

    @MainScreenScope
    @Provides
    fun provideMainGroupModel(mainGroupApi: MainGroupApi): MainGroupModel {
        return MainGroupModel(mainGroupApi)
    }
}