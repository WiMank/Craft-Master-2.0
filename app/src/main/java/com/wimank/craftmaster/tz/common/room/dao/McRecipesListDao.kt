package com.wimank.craftmaster.tz.common.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.wimank.craftmaster.tz.common.room.entities.McRecipesListEntity
import io.reactivex.Single

@Dao
interface McRecipesListDao {

    @Insert
    fun insert(mcRecipesListEntity: McRecipesListEntity)

    @Delete
    fun delete(mcRecipesListEntity: McRecipesListEntity)

    @Query("SELECT * FROM mc_recipes_list")
    fun getMcRecipesListFromDb(): Single<List<McRecipesListEntity>>

}