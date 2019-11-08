package com.wimank.craftmaster.tz.app.di.modules

import com.wimank.craftmaster.tz.app.di.scopes.AchievementsScope
import com.wimank.craftmaster.tz.app.mvp.models.AchievementsManager
import com.wimank.craftmaster.tz.app.mvp.presenters.AchievementsPresenter
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import dagger.Module
import dagger.Provides

@Module
class AchievementModule {

    @Provides
    @AchievementsScope
    fun provideAchievementsPresenter(achievementsManager: AchievementsManager): AchievementsPresenter {
        return AchievementsPresenter(achievementsManager)
    }

    @Provides
    @AchievementsScope
    fun provideAchievementsManager(
        craftMasterDataBase: CraftMasterDataBase
    ): AchievementsManager {
        return AchievementsManager(craftMasterDataBase)
    }
}
