package com.wimank.craftmaster.tz.recipe_screen.room

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.common.room.BaseDao
import io.reactivex.Single


@Dao
interface DescriptionDao : BaseDao<DescriptionEntity> {

    @Query("SELECT * FROM description_craft_recipes")
    fun getDescriptionFromDb(): Single<List<DescriptionEntity>>

}