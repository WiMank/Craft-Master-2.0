package com.wimank.craftmaster.tz.common.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MainGroupDao : BaseDao<MainGroupEntity> {

    @Query("SELECT * FROM main_group ORDER BY order_group")
    fun getFlowableMainGroupFromDb(): Flowable<List<MainGroupEntity>>

    @Query("SELECT * FROM main_group ORDER BY order_group")
    fun getMainGroupFromDb(): Single<List<MainGroupEntity>>
}