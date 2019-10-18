package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entitys.MobsEntity
import com.wimank.craftmaster.tz.common.room.BaseDao
import io.reactivex.Single

@Dao
interface MobsDao : BaseDao<MobsEntity> {

    @Query("SELECT * FROM mobs")
    fun getMobs(): Single<List<MobsEntity>>

}
