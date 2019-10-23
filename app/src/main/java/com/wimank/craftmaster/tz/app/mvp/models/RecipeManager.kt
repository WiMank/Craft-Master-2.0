package com.wimank.craftmaster.tz.app.mvp.models

import android.content.Context
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.room.LocalizedType
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entitys.RecipeEntity
import com.wimank.craftmaster.tz.app.utils.getCurrentLocale
import io.reactivex.Single

class RecipeManager(
    private val mContext: Context,
    private val craftMasterDataBase: CraftMasterDataBase
) {

    fun getDescriptionFromDb(recipeAttr: String): Single<DescriptionEntity> {
        return craftMasterDataBase.descriptionDao().getDescriptionByNameFromDb(recipeAttr)
    }

    fun getRecipeFromDb(recipeAttr: String): Single<RecipeEntity> {
        return craftMasterDataBase.recipeDao().getRecipesByNameFromDb(recipeAttr)
    }

    fun localizeString(localizedType: LocalizedType): String {
        return when (getCurrentLocale(mContext).language) {
            "ru" -> localizedType.getRuLocalization()
            "uk" -> localizedType.getRuLocalization()
            else -> localizedType.getEnLocalization()
        }
    }
}
