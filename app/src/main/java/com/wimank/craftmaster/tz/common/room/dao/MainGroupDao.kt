package com.wimank.craftmaster.tz.common.room.dao

import androidx.room.Dao
import androidx.room.Insert
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity

@Dao
interface MainGroupDao {

    @Insert
    fun insert(mainGroupEntity: MainGroupEntity)

}