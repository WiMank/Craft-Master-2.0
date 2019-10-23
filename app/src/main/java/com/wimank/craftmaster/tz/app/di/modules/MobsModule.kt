package com.wimank.craftmaster.tz.app.di.modules

import android.content.Context
import com.wimank.craftmaster.tz.app.di.scopes.MobsFragmentScope
import com.wimank.craftmaster.tz.app.mvp.models.MobsManager
import com.wimank.craftmaster.tz.app.mvp.presenters.MobsPresenter
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import dagger.Module
import dagger.Provides

@Module
class MobsModule {

    @Provides
    @MobsFragmentScope
    fun provideMobsPresenter(mobsManager: MobsManager): MobsPresenter {
        return MobsPresenter(mobsManager)
    }

    @Provides
    @MobsFragmentScope
    fun provideMobsManager(
        context: Context,
        craftMasterDataBase: CraftMasterDataBase
    ): MobsManager {
        return MobsManager(context, craftMasterDataBase)
    }
}
