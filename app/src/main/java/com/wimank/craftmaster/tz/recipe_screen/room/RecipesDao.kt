package com.wimank.craftmaster.tz.recipe_screen.room

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.common.room.BaseDao
import io.reactivex.Single

@Dao
interface RecipesDao : BaseDao<RecipeEntity> {

    @Query("SELECT * FROM craft_recipes")
    fun getRecipesFromDb(): Single<List<RecipeEntity>>
}
