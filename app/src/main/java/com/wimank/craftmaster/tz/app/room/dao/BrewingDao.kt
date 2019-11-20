package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entity.BrewingEntity
import io.reactivex.Single

@Dao
interface BrewingDao : BaseDao<BrewingEntity> {

    @Query("SELECT * FROM brewing")
    fun getBrewingVersions(): Single<List<BrewingEntity>>

}
