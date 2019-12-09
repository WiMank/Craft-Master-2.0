package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.mvp.common.IDataManager
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.room.entity.*
import com.wimank.craftmaster.tz.app.utils.ImageUtils
import io.reactivex.Single
import org.apache.commons.collections4.CollectionUtils

class DataManager(
    private val mApiManager: ApiManager,
    private val mImageUtils: ImageUtils,
    private val mLocaleManager: LocaleManager,
    private val mCraftMasterDataBase: CraftMasterDataBase
) : IDataManager<BaseEntity> {

    override fun containsData(serAr: List<BaseEntity>, locAr: List<BaseEntity>) {
        if (serAr.isNotEmpty()) {
            val disjunctionArray =
                CollectionUtils.disjunction(serAr, locAr).sortedWith(compareBy { it.getVersion() })

            disjunctionArray.forEach { entity ->
                if (locAr.contains(entity))
                    deleteEntity(entity)
                else
                    downloadImageAndInsertEntity(entity)
            }
        }
    }

    private fun downloadImageAndInsertEntity(entity: BaseEntity) {
        if (!mImageUtils.checkImageExist(entity.getImage())) {
            if (entity is BrewingEntity) {
                downloadBrewingImage(entity)
                return
            }
            with(mApiManager.imageApi.downloadImage(entity.getImage()).execute()) {
                if (isSuccessful) {
                    body()?.byteStream()?.let {
                        mImageUtils.writeImage(
                            it,
                            entity.getImage()
                        )
                    }
                }
            }
        }
        insertEntity(entity)
    }

    private fun downloadBrewingImage(entity: BrewingEntity) {
        with(
            mApiManager.brewingApi.downloadBrewingImage(
                mLocaleManager.getCurrentLocale().language
            ).execute()
        ) {
            if (isSuccessful) {
                body()?.byteStream()?.let {
                    mImageUtils.writeImage(
                        it,
                        entity.getImage()
                    )
                }
            }
        }
    }

    fun insertDbVers(dbVersionEntity: DbVersionEntity) {
        mCraftMasterDataBase.dbVersionDao().insert(dbVersionEntity)
    }

    override fun insertEntity(entity: BaseEntity) {
        when (entity) {
            is DescriptionEntity -> mCraftMasterDataBase.descriptionDao().insert(entity)
            is RecipeEntity -> mCraftMasterDataBase.recipeDao().insert(entity)
            is MobsEntity -> mCraftMasterDataBase.mobsDao().insert(entity)
            is DeviceEntity -> mCraftMasterDataBase.devicesDao().insert(entity)
            is AchievementEntity -> mCraftMasterDataBase.achievementDao().insert(entity)
            is BiomesEntity -> mCraftMasterDataBase.biomesDao().insert(entity)
            is BrewingEntity -> mCraftMasterDataBase.brewingDao().insert(entity)
            is AdditionalEntity -> mCraftMasterDataBase.additionalDao().insert(entity)
        }
    }

    override fun deleteEntity(entity: BaseEntity) {
        when (entity) {
            is DescriptionEntity -> mCraftMasterDataBase.descriptionDao().delete(entity)
            is RecipeEntity -> mCraftMasterDataBase.recipeDao().delete(entity)
            is MobsEntity -> mCraftMasterDataBase.mobsDao().delete(entity)
            is DeviceEntity -> mCraftMasterDataBase.devicesDao().delete(entity)
            is AchievementEntity -> mCraftMasterDataBase.achievementDao().delete(entity)
            is BiomesEntity -> mCraftMasterDataBase.biomesDao().delete(entity)
            is BrewingEntity -> mCraftMasterDataBase.brewingDao().delete(entity)
            is AdditionalEntity -> mCraftMasterDataBase.additionalDao().delete(entity)
        }
    }

    fun getRecipes() = mApiManager.recipesApi.getRecipes()

    fun getRecipesFromDb(): Single<List<RecipeEntity>> {
        return mCraftMasterDataBase.recipeDao().getRecipesFromDb()
    }

    fun getDescriptionFromDb(): Single<List<DescriptionEntity>> {
        return mCraftMasterDataBase.descriptionDao().getDescriptionFromDb()
    }

    fun getMobs() = mApiManager.mobsApi.getMobs()

    fun getMobsFromDb() = mCraftMasterDataBase.mobsDao().getMobs()

    fun getManufacturingDevices() = mApiManager.devicesApi.getDevices()

    fun getManufacturingDevicesDaoFromDb() =
        mCraftMasterDataBase.devicesDao().getDevices()

    fun getAchievements() = mApiManager.achievementsApi.getAchievements()

    fun getAchievementsFromDb(): Single<List<AchievementEntity>> {
        return mCraftMasterDataBase.achievementDao().getAllAchievements()
    }

    fun getBiomes() = mApiManager.biomesApi.getBiomes()

    fun getBiomesFromDb(): Single<List<BiomesEntity>> {
        return mCraftMasterDataBase.biomesDao().getBiomes()
    }

    fun getBrewing() = mApiManager.brewingApi.getVersBrewingImages()

    fun getBrewingFromDb(): Single<List<BrewingEntity>> {
        return mCraftMasterDataBase.brewingDao().getBrewingVersions()
    }

    fun getAddInfo() = mApiManager.addInfoApi.getAddInfo()

    fun getAddInfoFromDb(): Single<List<AdditionalEntity>> {
        return mCraftMasterDataBase.additionalDao().getAdditionalInfoList()
    }

    fun getDbVers() = mApiManager.dbVersApi.getDbVers()

    fun getDbVersFromDb(): Single<DbVersionEntity> {
        return mCraftMasterDataBase.dbVersionDao().getDbVersFromDb()
    }
}
