package com.wimank.craftmaster.tz.common.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MainGroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mainGroupEntity: MainGroupEntity)

    @Query("SELECT * FROM main_group ORDER BY order_group")
    fun getFlowableMainGroupFromDb(): Flowable<List<MainGroupEntity>>

    @Query("DELETE FROM main_group")
    fun delete()

}