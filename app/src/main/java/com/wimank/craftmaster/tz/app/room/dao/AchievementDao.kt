package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entity.AchievementEntity
import io.reactivex.Single

@Dao
interface AchievementDao : BaseDao<AchievementEntity> {

    @Query("SELECT * FROM achievements ORDER BY ach_modification DESC")
    fun getAllAchievements(): Single<List<AchievementEntity>>

}
