package com.wimank.craftmaster.tz.common.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.wimank.craftmaster.tz.common.room.entities.CategoriesEntity
import io.reactivex.Single

@Dao
interface CategoriesDao {

    @Insert
    fun insert(categoriesEntity: CategoriesEntity)

    @Delete
    fun delete(categoriesEntity: CategoriesEntity)

    @Query("SELECT * FROM categories")
    fun getCategoriesFromDb(): Single<List<CategoriesEntity>>
}