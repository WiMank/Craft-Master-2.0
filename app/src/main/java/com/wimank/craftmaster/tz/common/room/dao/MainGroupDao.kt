package com.wimank.craftmaster.tz.common.room.dao

import androidx.room.*
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import io.reactivex.Flowable

@Dao
interface MainGroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mainGroupEntity: MainGroupEntity)

    @Query("SELECT * FROM main_group ORDER BY order_group")
    fun getMainGroup(): Flowable<List<MainGroupEntity>>

    @Query("SELECT * FROM main_group ORDER BY order_group")
    fun getMainGroupTest(): List<MainGroupEntity>

    @Delete
    fun delete(mainGroupEntity: MainGroupEntity)

}