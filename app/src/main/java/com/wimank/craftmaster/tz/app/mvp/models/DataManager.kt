package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.rest.api.MobsApi
import com.wimank.craftmaster.tz.app.rest.api.RecipesApi
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entitys.RecipeEntity
import com.wimank.craftmaster.tz.common.rest.ImageApi
import com.wimank.craftmaster.tz.common.room.BaseEntity
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.utils.ImageUtils
import io.reactivex.Single
import org.apache.commons.collections4.CollectionUtils

class DataManager(
    private val mImageUtils: ImageUtils,
    private val mImageApi: ImageApi,
    private val mRecipesApi: RecipesApi,
    private val mMobsApi: MobsApi,
    private val mCraftMasterDataBase: CraftMasterDataBase
) : IDataManager<BaseEntity> {

    override fun containsData(serAr: List<BaseEntity>, locAr: List<BaseEntity>) {
        if (serAr.isNotEmpty()) {
            val disjunctionArray =
                CollectionUtils.disjunction(
                    serAr,
                    locAr
                ).sortedWith(compareBy { it.getVersion() })
            disjunctionArray.forEach { entity ->
                if (locAr.contains(entity))
                    deleteEntity(entity)
                else
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
        }
    }

    override fun insertEntity(entity: BaseEntity) {
        when (entity) {
            is RecipeEntity -> mCraftMasterDataBase.recipeDao().insert(entity)
            is DescriptionEntity -> mCraftMasterDataBase.descriptionDao().insert(entity)
        }
    }

    override fun deleteEntity(entity: BaseEntity) {
        when (entity) {
            is RecipeEntity -> mCraftMasterDataBase.recipeDao().delete(entity)
            is DescriptionEntity -> mCraftMasterDataBase.descriptionDao().delete(entity)
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

}
