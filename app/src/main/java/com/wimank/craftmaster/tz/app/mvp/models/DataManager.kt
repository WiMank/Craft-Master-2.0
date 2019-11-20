package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.mvp.common.IDataManager
import com.wimank.craftmaster.tz.app.rest.api.*
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.room.entity.*
import com.wimank.craftmaster.tz.app.utils.ImageUtils
import io.reactivex.Single
import org.apache.commons.collections4.CollectionUtils

class DataManager(
    private val mImageUtils: ImageUtils,
    private val mImageApi: ImageApi,
    private val mRecipesApi: RecipesApi,
    private val mMobsApi: MobsApi,
    private val mDevicesApi: DevicesApi,
    private val mAchievementsApi: AchievementsApi,
    private val mBiomesApi: BiomesApi,
    private val mCraftMasterDataBase: CraftMasterDataBase
) : IDataManager<BaseEntity> {

    override fun containsData(serAr: List<BaseEntity>, locAr: List<BaseEntity>) {
        if (serAr.isNotEmpty()) {
            val disjunctionArray =
                CollectionUtils.disjunction(serAr, locAr)
                    .sortedWith(compareBy { it.getVersion() })
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
            with(mImageApi.downloadImage(entity.getImage()).execute()) {
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

    override fun insertEntity(entity: BaseEntity) {
        when (entity) {
            is RecipeEntity -> mCraftMasterDataBase.recipeDao().insert(entity)
            is DescriptionEntity -> mCraftMasterDataBase.descriptionDao().insert(entity)
            is MobsEntity -> mCraftMasterDataBase.mobsDao().insert(entity)
            is DeviceEntity -> mCraftMasterDataBase.devicesDao().insert(entity)
            is AchievementEntity -> mCraftMasterDataBase.achievementDao().insert(entity)
            is BiomesEntity -> mCraftMasterDataBase.biomesDao().insert(entity)
            is BrewingEntity -> mCraftMasterDataBase.brewingDao().insert(entity)
        }
    }

    override fun deleteEntity(entity: BaseEntity) {
        when (entity) {
            is RecipeEntity -> mCraftMasterDataBase.recipeDao().delete(entity)
            is DescriptionEntity -> mCraftMasterDataBase.descriptionDao().delete(entity)
            is MobsEntity -> mCraftMasterDataBase.mobsDao().delete(entity)
            is DeviceEntity -> mCraftMasterDataBase.devicesDao().delete(entity)
            is AchievementEntity -> mCraftMasterDataBase.achievementDao().delete(entity)
            is BiomesEntity -> mCraftMasterDataBase.biomesDao().delete(entity)
            is BrewingEntity -> mCraftMasterDataBase.brewingDao().delete(entity)
        }
    }

    fun getRecipes() = mRecipesApi.getRecipes()

    fun getRecipesFromDb(): Single<List<RecipeEntity>> {
        return mCraftMasterDataBase.recipeDao().getRecipesFromDb()
    }

    fun getDescriptionFromDb(): Single<List<DescriptionEntity>> {
        return mCraftMasterDataBase.descriptionDao().getDescriptionFromDb()
    }

    fun getMobs() = mMobsApi.getMobs()

    fun getMobsFromDb() = mCraftMasterDataBase.mobsDao().getMobs()

    fun getManufacturingDevices() = mDevicesApi.getDevices()

    fun getManufacturingDevicesDaoFromDb() =
        mCraftMasterDataBase.devicesDao().getDevices()

    fun getAchievements() = mAchievementsApi.getAchievements()

    fun getAchievementsFromDb(): Single<List<AchievementEntity>> {
        return mCraftMasterDataBase.achievementDao().getAllAchievements()
    }

    fun getBiomes() = mBiomesApi.getBiomes()

    fun getBiomesFromDb(): Single<List<BiomesEntity>> {
        return mCraftMasterDataBase.biomesDao().getBiomes()
    }

}
