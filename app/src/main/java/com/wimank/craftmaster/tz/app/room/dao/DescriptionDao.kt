package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entity.DescriptionEntity
import io.reactivex.Single

@Dao
interface DescriptionDao : BaseDao<DescriptionEntity> {

    @Query("SELECT * FROM description_craft_recipes")
    fun getDescriptionFromDb(): Single<List<DescriptionEntity>>

    @Query("SELECT * FROM description_craft_recipes WHERE recipeAttr =:pRecipeAttr")
    fun getDescriptionByNameFromDb(pRecipeAttr: String): Single<DescriptionEntity>

    @Query("SELECT * FROM description_craft_recipes WHERE modification =:pModification ORDER BY recipeName ASC")
    fun getRecipesListFromDb(pModification: String): Single<List<DescriptionEntity>>

    @Query("SELECT * FROM description_craft_recipes ORDER BY recipeName ASC")
    fun getAllRecipesListFromDb(): Single<List<DescriptionEntity>>

}
