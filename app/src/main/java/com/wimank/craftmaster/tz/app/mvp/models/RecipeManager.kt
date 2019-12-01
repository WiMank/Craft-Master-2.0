package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.room.entity.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entity.DeviceEntity
import com.wimank.craftmaster.tz.app.room.entity.FavoritesEntity
import com.wimank.craftmaster.tz.app.room.entity.RecipeEntity
import io.reactivex.Maybe
import io.reactivex.Single

class RecipeManager(
    private val mLocaleManager: LocaleManager,
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
            else -> mLocaleManager.getContext().getString(R.string.recipe_craft_text)
        }
    }

    fun getMachine(deviceEntity: DeviceEntity): Machine {
        return Machine(localizeString(deviceEntity.machineName), deviceEntity.machine)
    }

    fun localizeString(localizedType: LocalizedType): String {
        return mLocaleManager.localizeString(localizedType)
    }

    fun addToFavorites(favoritesEntity: FavoritesEntity) {
        craftMasterDataBase.favoritesDao().insert(favoritesEntity)
    }

    fun deleteFromFavorites(favoritesEntity: FavoritesEntity) {
        craftMasterDataBase.favoritesDao().delete(favoritesEntity)
    }

    fun checkFavorite(recipeAttr: String): Boolean {
        return with(craftMasterDataBase.favoritesDao().checkFavorite(recipeAttr)) {
            this?.fRecipeAttr == recipeAttr
        }
    }
}
