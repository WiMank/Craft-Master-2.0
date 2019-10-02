package com.wimank.craftmaster.tz.common.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: T)

    @Delete
    fun delete(entity: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(entity: T)

}