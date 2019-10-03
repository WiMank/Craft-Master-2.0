package com.wimank.craftmaster.tz.recipe_screen.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.common.room.dao.BaseDao
import com.wimank.craftmaster.tz.recipe_screen.room.entity.McRecipeEntity
import io.reactivex.Single


@Dao
interface McRecipeDao : BaseDao<McRecipeEntity> {

    @Query("SELECT * FROM mc_craft_recipe")
    fun getRecipeFromDb(): Single<List<McRecipeEntity>>
}