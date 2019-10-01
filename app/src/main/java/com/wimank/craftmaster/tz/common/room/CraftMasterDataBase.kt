package com.wimank.craftmaster.tz.common.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wimank.craftmaster.tz.common.room.dao.CategoriesDao
import com.wimank.craftmaster.tz.common.room.dao.MainGroupDao
import com.wimank.craftmaster.tz.common.room.dao.McRecipesListDao
import com.wimank.craftmaster.tz.common.room.entities.CategoriesEntity
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.room.entities.McRecipesListEntity


@Database(
    entities = [
        MainGroupEntity::class,
        CategoriesEntity::class,
        McRecipesListEntity::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class CraftMasterDataBase : RoomDatabase() {
    abstract fun mainGroupDao() : MainGroupDao
    abstract fun categoriesDao() : CategoriesDao
    abstract fun mcRecipesListDao(): McRecipesListDao
}