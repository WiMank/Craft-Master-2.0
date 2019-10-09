package com.wimank.craftmaster.tz.categories_screen.room

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.common.room.BaseDao
import io.reactivex.Single

@Dao
interface CategoryDao : BaseDao<CategoryEntity> {

    @Query("SELECT * FROM categories ORDER BY orderCategories")
    fun getCategoriesFromDb(): Single<List<CategoryEntity>>

    @Query("SELECT * FROM categories WHERE `group` =:pGroup ORDER BY orderCategories")
    fun getCategoriesByGroupName(pGroup: String): Single<List<CategoryEntity>>

}