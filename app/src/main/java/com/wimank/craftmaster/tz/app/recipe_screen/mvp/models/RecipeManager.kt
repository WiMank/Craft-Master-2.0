package com.wimank.craftmaster.tz.app.recipe_screen.mvp.models

import com.wimank.craftmaster.tz.app.recipe_screen.room.DescriptionEntity
import com.wimank.craftmaster.tz.app.recipe_screen.room.RecipeEntity
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import io.reactivex.Single

class RecipeManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun getDescriptionFromDb(recipeAttr: String): Single<List<DescriptionEntity>> {
        return craftMasterDataBase.descriptionDao().getDescriptionByNameFromDb(recipeAttr)
    }

    fun getRecipeFromDb(recipeAttr: String): Single<List<RecipeEntity>> {
        return craftMasterDataBase.recipeDao().getRecipesByNameFromDb(recipeAttr)
    }
}
