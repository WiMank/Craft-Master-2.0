package com.wimank.craftmaster.tz.main_screen.di

import android.content.Context
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupModel
import com.wimank.craftmaster.tz.main_screen.mvp.presenters.MainPresenter
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainActModule {

    @MainScope
    @Provides
    fun provideMainPresenter(context: Context, mainGroupModel : MainGroupModel) = MainPresenter(context, mainGroupModel)

    @MainScope
    @Provides
    fun provideMainGroupModel(mainGroupApi: MainGroupApi): MainGroupModel {
        return MainGroupModel(mainGroupApi)
    }
}