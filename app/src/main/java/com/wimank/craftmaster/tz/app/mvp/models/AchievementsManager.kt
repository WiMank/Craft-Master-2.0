package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.room.entitys.AchievementEntity
import io.reactivex.Single

class AchievementsManager(
    private val mCraftMasterDataBase: CraftMasterDataBase
) {

    fun getAchievementsFromDb(): Single<List<AchievementEntity>> {
        return mCraftMasterDataBase.achievementDao().getAllAchievements()
    }
}
