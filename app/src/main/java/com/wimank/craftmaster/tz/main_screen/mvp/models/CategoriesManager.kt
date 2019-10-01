package com.wimank.craftmaster.tz.main_screen.mvp.models

import android.content.Context
import com.wimank.craftmaster.tz.common.rest.ImageApi
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.room.entities.CategoriesEntity
import com.wimank.craftmaster.tz.common.utils.checkImageExist
import com.wimank.craftmaster.tz.common.utils.writeImage
import com.wimank.craftmaster.tz.main_screen.rest.CategoriesApi
import com.wimank.craftmaster.tz.main_screen.rest.response.CategoriesResponse
import io.reactivex.Single
import org.apache.commons.collections4.CollectionUtils


class CategoriesManager(
    private val mContext: Context,
    private val mCategoriesApi: CategoriesApi,
    private val mImageApi: ImageApi,
    private val mCraftMasterDataBase: CraftMasterDataBase
) {

    fun containsData(serAr: List<CategoriesEntity>, locAr: List<CategoriesEntity>) {
        if (serAr.isNotEmpty()) {
            val disjunctionArray =
                CollectionUtils.disjunction(
                    serAr,
                    locAr
                ).sortedWith(compareBy { it.vers })

            disjunctionArray.forEach {
                if (locAr.contains(it))
                    deleteCategoriesEntity(it)
                else
                    downloadImageAndInsertEntity(it)
            }
        }
    }

    private fun downloadImageAndInsertEntity(categoriesEntity: CategoriesEntity) {
        if (!checkImageExist(mContext, categoriesEntity.categoryImage)) {
            with(mImageApi.downloadImage(categoriesEntity.categoryImage).execute()) {
                if (isSuccessful) {
                    body()?.byteStream()?.let {
                        writeImage(
                            mContext,
                            it,
                            categoriesEntity.categoryImage
                        )
                    }
                }
            }
        }
        insertCategoriesEntity(categoriesEntity)
    }

    private fun insertCategoriesEntity(categoriesEntity: CategoriesEntity) {
        return mCraftMasterDataBase.categoriesDao().insert(categoriesEntity)
    }

    private fun deleteCategoriesEntity(categoriesEntity: CategoriesEntity) {
        return mCraftMasterDataBase.categoriesDao().delete(categoriesEntity)
    }

    fun getCategories(): Single<CategoriesResponse> {
        return mCategoriesApi.getCategories()
    }

    fun getCategoriesFromDb(): Single<List<CategoriesEntity>> {
        return mCraftMasterDataBase.categoriesDao().getCategoriesFromDb()
    }
}