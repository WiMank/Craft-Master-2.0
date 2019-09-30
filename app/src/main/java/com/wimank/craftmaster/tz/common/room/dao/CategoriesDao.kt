package com.wimank.craftmaster.tz.common.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.wimank.craftmaster.tz.common.room.entities.CategoriesEntity

@Dao
interface CategoriesDao {

    @Insert
    fun insert(categoriesEntity: CategoriesEntity)

    @Delete
    fun delete(categoriesEntity: CategoriesEntity)

}