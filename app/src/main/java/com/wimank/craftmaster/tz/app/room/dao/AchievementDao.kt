package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entitys.AchievementEntity
import io.reactivex.Single

@Dao
interface AchievementDao : BaseDao<AchievementEntity> {

    @Query("SELECT * FROM achievements WHERE ach_modification = :achModification ORDER BY ach_name")
    fun getAchievementsByName(achModification: String): Single<List<AchievementEntity>>

    @Query("SELECT * FROM achievements")
    fun getAllAchievements(): Single<List<AchievementEntity>>

}
