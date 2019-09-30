package com.wimank.craftmaster.tz.common.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wimank.craftmaster.tz.common.room.dao.CategoriesDao
import com.wimank.craftmaster.tz.common.room.dao.MainGroupDao
import com.wimank.craftmaster.tz.common.room.entities.CategoriesEntity
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity


@Database(
    entities = [
        MainGroupEntity::class,
        CategoriesEntity::class],
    exportSchema = false,
    version = 1
)
abstract class CraftMasterDataBase : RoomDatabase() {
    abstract fun mainGroupDao() : MainGroupDao
    abstract fun categoriesDao() : CategoriesDao
}