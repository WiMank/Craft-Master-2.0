package com.wimank.craftmaster.tz.app.mvp.models

import android.content.Context
import com.wimank.craftmaster.tz.app.rest.responses.*
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
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

    fun localizedName(recipeName: RecipeName): String {
        return when (getCurrentLocale(mContext).language) {
            "ru" -> recipeName.ru ?: ""
            "uk" -> recipeName.ru ?: ""
            else -> recipeName.en ?: ""
        }
    }

    fun localizeDescription(descriptionCraft: DescriptionCraft): String {
        return when (getCurrentLocale(mContext).language) {
            "ru" -> descriptionCraft.ru
            "uk" -> descriptionCraft.ru
            else -> descriptionCraft.en
        }
    }

    fun localizeLeftPar(leftParameter: LeftParameter): String {
        return when (getCurrentLocale(mContext).language) {
            "ru" -> leftParameter.ru
            "uk" -> leftParameter.ru
            else -> leftParameter.en
        }
    }

    fun localizeLeftParText(leftParameterText: LeftParameterText): String {
        return when (getCurrentLocale(mContext).language) {
            "ru" -> leftParameterText.ru
            "uk" -> leftParameterText.ru
            else -> leftParameterText.en
        }
    }

    fun localizeRightPar(rightParameter: RightParameter): String {
        return when (getCurrentLocale(mContext).language) {
            "ru" -> rightParameter.ru
            "uk" -> rightParameter.ru
            else -> rightParameter.en
        }
    }

    fun localizeRightParText(rightParameterText: RightParameterText): String {
        return when (getCurrentLocale(mContext).language) {
            "ru" -> rightParameterText.ru
            "uk" -> rightParameterText.ru
            else -> rightParameterText.en
        }
    }
}
