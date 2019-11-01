package com.wimank.craftmaster.tz.app.mvp.models

import android.content.Context
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entitys.DeviceEntity
import com.wimank.craftmaster.tz.app.room.entitys.RecipeEntity
import com.wimank.craftmaster.tz.app.utils.getCurrentLocale
import io.reactivex.Maybe
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

    fun getDeviceFromDb(recipeAttr: String): Maybe<DeviceEntity> {
        return craftMasterDataBase.devicesDao().getDeviceByName(recipeAttr)
    }

    fun getDeviceName(deviceEntity: DeviceEntity): String {
        return when {
            deviceEntity.furnace.stringIsNotEmpty() -> localizeString(deviceEntity.furnace)
            deviceEntity.extractor.stringIsNotEmpty() -> localizeString(deviceEntity.extractor)
            deviceEntity.crusher.stringIsNotEmpty() -> localizeString(deviceEntity.crusher)
            deviceEntity.recycler.stringIsNotEmpty() -> localizeString(deviceEntity.recycler)
            deviceEntity.compressor.stringIsNotEmpty() -> localizeString(deviceEntity.compressor)
            deviceEntity.machineName.stringIsNotEmpty() -> localizeString(deviceEntity.machineName)
            else -> mContext.getString(R.string.recipe_craft_text)
        }
    }

    fun getMachine(deviceEntity: DeviceEntity): Machine {
        return Machine(localizeString(deviceEntity.machineName), deviceEntity.machine)
    }

    fun localizeString(localizedType: LocalizedType): String {
        return when (getCurrentLocale(mContext).language) {
            "ru" -> localizedType.ru
            "uk" -> localizedType.ru
            else -> localizedType.en
        }
    }
}
