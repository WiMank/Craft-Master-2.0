package com.wimank.craftmaster.tz.main_screen.mvp.models

import android.content.Context
import com.wimank.craftmaster.tz.categories_screen.rest.CategoriesApi
import com.wimank.craftmaster.tz.categories_screen.rest.CategoryResponse
import com.wimank.craftmaster.tz.common.rest.ImageApi
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.room.entities.*
import com.wimank.craftmaster.tz.common.utils.checkImageExist
import com.wimank.craftmaster.tz.common.utils.writeImage
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupResponse
import com.wimank.craftmaster.tz.recipe_screen.rest.RecipeApi
import com.wimank.craftmaster.tz.recipe_screen.rest.RecipeResponse
import com.wimank.craftmaster.tz.recipe_screen.room.entity.McDescriptionEntity
import com.wimank.craftmaster.tz.recipe_screen.room.entity.McRecipeEntity
import io.reactivex.Flowable
import io.reactivex.Single
import org.apache.commons.collections4.CollectionUtils


class DataManager(
    private val mContext: Context,
    private val mCategoriesApi: CategoriesApi,
    private val mainGroupApi: MainGroupApi,
    private val mImageApi: ImageApi,
    private val mRecipeApi: RecipeApi,
    private val mCraftMasterDataBase: CraftMasterDataBase
) : IDataManager<BaseEntity> {

    override fun containsData(serAr: List<BaseEntity>, locAr: List<BaseEntity>) {
        if (serAr.isNotEmpty()) {
            val disjunctionArray =
                CollectionUtils.disjunction(
                    serAr,
                    locAr
                ).sortedWith(compareBy { it.getVersion() })

            disjunctionArray.forEach {
                if (locAr.contains(it))
                    deleteEntity(it)
                else
                    downloadImageAndInsertEntity(it)
            }
        }
    }

    private fun downloadImageAndInsertEntity(entity: BaseEntity) {
        if (!checkImageExist(mContext, entity.getImage())) {
            with(mImageApi.downloadImage(entity.getImage()).execute()) {
                if (isSuccessful) {
                    body()?.byteStream()?.let {
                        writeImage(
                            mContext,
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
            is MainGroupEntity -> mCraftMasterDataBase.mainGroupDao().insert(entity)
            is McCategoryEntity -> mCraftMasterDataBase.mcCategoryDao().insert(entity)
            is BcCategoryEntity -> mCraftMasterDataBase.bcCategoryDao().insert(entity)
            is IcCategoryEntity -> mCraftMasterDataBase.icCategoryDao().insert(entity)
            is FrCategoryEntity -> mCraftMasterDataBase.frCategoryDao().insert(entity)
        }
    }

    override fun deleteEntity(entity: BaseEntity) {
        when (entity) {
            is MainGroupEntity -> mCraftMasterDataBase.mainGroupDao().delete(entity)
            is McCategoryEntity -> mCraftMasterDataBase.mcCategoryDao().delete(entity)
            is BcCategoryEntity -> mCraftMasterDataBase.bcCategoryDao().delete(entity)
            is IcCategoryEntity -> mCraftMasterDataBase.icCategoryDao().delete(entity)
            is FrCategoryEntity -> mCraftMasterDataBase.frCategoryDao().delete(entity)
        }
    }

    fun getMainGroup(): Single<MainGroupResponse> {
        return mainGroupApi.getMainGroupList()
    }


    fun getMcCategory(): Single<CategoryResponse<McCategoryEntity>> {
        return mCategoriesApi.getMcCategory()
    }

    fun getRecipes(): Single<RecipeResponse> {
        return mRecipeApi.getRecipes()
    }

    fun getFlowableMainGroupFromDb(): Flowable<List<MainGroupEntity>> {
        return mCraftMasterDataBase.mainGroupDao().getFlowableMainGroupFromDb()
    }

    fun getMainGroupFromDb(): Single<List<MainGroupEntity>> {
        return mCraftMasterDataBase.mainGroupDao().getMainGroupFromDb()
    }

    fun getMcCategoryFromDb(): Single<List<McCategoryEntity>> {
        return mCraftMasterDataBase.mcCategoryDao().getMcCategoryFromDb()
    }

    fun getRecipeFromDb(): Single<List<McRecipeEntity>> {
        return mCraftMasterDataBase.mcRecipeDao().getRecipeFromDb()
    }

    fun getDescriptionFromDb(): Single<List<McDescriptionEntity>> {
        return mCraftMasterDataBase.mcDescriptionDao().getDescriptionFromDb()
    }
}