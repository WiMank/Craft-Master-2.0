package com.wimank.craftmaster.tz.common.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wimank.craftmaster.tz.app.room.dao.CategoryDao
import com.wimank.craftmaster.tz.app.room.dao.DescriptionDao
import com.wimank.craftmaster.tz.app.room.dao.MainGroupDao
import com.wimank.craftmaster.tz.app.room.dao.RecipesDao
import com.wimank.craftmaster.tz.app.room.entitys.CategoryEntity
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entitys.MainGroupEntity
import com.wimank.craftmaster.tz.app.room.entitys.RecipeEntity

@Database(
    entities = [
        MainGroupEntity::class,
        CategoryEntity::class,
        DescriptionEntity::class,
        RecipeEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class CraftMasterDataBase : RoomDatabase() {
    abstract fun mainGroupDao() : MainGroupDao
    abstract fun categoryDao(): CategoryDao
    abstract fun descriptionDao(): DescriptionDao
    abstract fun recipeDao(): RecipesDao
}
