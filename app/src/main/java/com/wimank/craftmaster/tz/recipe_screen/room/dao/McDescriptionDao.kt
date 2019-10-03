package com.wimank.craftmaster.tz.recipe_screen.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.common.room.dao.BaseDao
import com.wimank.craftmaster.tz.recipe_screen.room.entity.McDescriptionEntity
import io.reactivex.Single


@Dao
interface McDescriptionDao : BaseDao<McDescriptionEntity> {

    @Query("SELECT * FROM mc_description")
    fun getDescriptionFromDb(): Single<List<McDescriptionEntity>>

}