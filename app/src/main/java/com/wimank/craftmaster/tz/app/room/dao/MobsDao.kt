package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entitys.MobsEntity
import io.reactivex.Single

@Dao
interface MobsDao : BaseDao<MobsEntity> {

    @Query("SELECT * FROM mobs ORDER BY mob_name ASC")
    fun getMobs(): Single<List<MobsEntity>>
}
