package com.wimank.craftmaster.tz.app.di

import android.content.Context
import com.wimank.craftmaster.tz.app.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        ActivityModule::class,
        ApiModule::class,
        RoomModule::class,
        RetrofitModule::class]
)
interface AppComponent : AndroidInjector<CraftMasterApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}
