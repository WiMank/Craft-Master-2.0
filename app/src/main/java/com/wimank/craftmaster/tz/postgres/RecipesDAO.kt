package com.wimank.craftmaster.tz.postgres

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipesDAO {

    @Query("SELECT * FROM cmrecipes WHERE _id = :id")
    fun getById(id: Int): RecipesEntity


    @Query("SELECT * FROM  cmrecipes")
    fun getAll(): List<RecipesEntity>


    @Insert
    fun insert(recipesEntity: RecipesEntity): Long

}