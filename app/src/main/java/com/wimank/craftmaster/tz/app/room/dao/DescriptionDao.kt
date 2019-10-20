package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.RecipesListItem
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionEntity
import com.wimank.craftmaster.tz.common.room.BaseDao
import io.reactivex.Single

@Dao
interface DescriptionDao : BaseDao<DescriptionEntity> {

    @Query("SELECT * FROM description_craft_recipes")
    fun getDescriptionFromDb(): Single<List<DescriptionEntity>>

    @Query("SELECT * FROM description_craft_recipes WHERE recipeAttr =:pRecipeAttr")
    fun getDescriptionByNameFromDb(pRecipeAttr: String): Single<DescriptionEntity>

    @Query("SELECT recipeName, recipeImageName, recipeAttr FROM description_craft_recipes ORDER BY recipeName ASC")
    fun getRecipesListFromDb(): Single<List<RecipesListItem>>

}
