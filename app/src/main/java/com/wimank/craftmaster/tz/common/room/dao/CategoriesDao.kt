package com.wimank.craftmaster.tz.common.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.common.room.entities.BcCategoryEntity
import com.wimank.craftmaster.tz.common.room.entities.FrCategoryEntity
import com.wimank.craftmaster.tz.common.room.entities.IcCategoryEntity
import com.wimank.craftmaster.tz.common.room.entities.McCategoryEntity
import io.reactivex.Single

@Dao
interface McCategoryDao : BaseDao<McCategoryEntity> {

    @Query("SELECT * FROM mc_categories")
    fun getMcCategoryFromDb(): Single<List<McCategoryEntity>>
}

@Dao
interface BcCategoryDao : BaseDao<BcCategoryEntity> {

    @Query("SELECT * FROM bc_categories")
    fun getBcCategoryFromDb(): Single<List<BcCategoryEntity>>
}

@Dao
interface IcCategoryDao : BaseDao<IcCategoryEntity> {

    @Query("SELECT * FROM ic_categories")
    fun getIcCategoryFromDb(): Single<List<IcCategoryEntity>>
}

@Dao
interface FrCategoryDao : BaseDao<FrCategoryEntity> {

    @Query("SELECT * FROM fr_categories")
    fun getFrCategoryFromDb(): Single<List<FrCategoryEntity>>
}