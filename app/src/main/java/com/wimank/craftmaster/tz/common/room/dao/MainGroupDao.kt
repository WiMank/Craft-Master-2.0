package com.wimank.craftmaster.tz.common.room.dao

import androidx.room.*
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import io.reactivex.Single

@Dao
interface MainGroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mainGroupEntity: MainGroupEntity)

    @Query("SELECT * FROM main_group ORDER BY order_group")
    fun getMainGroupFromDb(): Single<List<MainGroupEntity>>

    @Delete
    fun delete(mainGroupEntity: MainGroupEntity)

}