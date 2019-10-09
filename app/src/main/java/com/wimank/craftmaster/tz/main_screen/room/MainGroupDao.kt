package com.wimank.craftmaster.tz.main_screen.room

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.common.room.BaseDao
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MainGroupDao : BaseDao<MainGroupEntity> {

    @Query("SELECT * FROM main_group ORDER BY order_group")
    fun getFlowableMainGroupFromDb(): Flowable<List<MainGroupEntity>>

    @Query("SELECT * FROM main_group ORDER BY order_group")
    fun getMainGroupFromDb(): Single<List<MainGroupEntity>>
}
