package com.wimank.craftmaster.tz.app.recipe_screen.room

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.common.room.BaseDao
import io.reactivex.Single

@Dao
interface RecipesDao : BaseDao<RecipeEntity> {

    @Query("SELECT * FROM craft_recipes")
    fun getRecipesFromDb(): Single<List<RecipeEntity>>

    @Query("SELECT * FROM craft_recipes WHERE recipeAttr=:pRecipeAttr")
    fun getRecipesByNameFromDb(pRecipeAttr: String): Single<List<RecipeEntity>>
}
