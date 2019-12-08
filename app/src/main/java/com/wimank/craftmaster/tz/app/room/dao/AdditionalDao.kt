package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entity.AdditionalEntity
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface AdditionalDao : BaseDao<AdditionalEntity> {

    @Query("SELECT * FROM additional_info")
    fun getAdditionalInfoList(): Single<List<AdditionalEntity>>

    @Query("SELECT * FROM additional_info WHERE recipeAttr =:pRecipeAttr")
    fun getAddInfoByName(pRecipeAttr: String): Maybe<AdditionalEntity>

}
