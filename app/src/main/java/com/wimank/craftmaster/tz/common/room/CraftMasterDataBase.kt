package com.wimank.craftmaster.tz.common.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wimank.craftmaster.tz.app.room.dao.*
import com.wimank.craftmaster.tz.app.room.entitys.*

@Database(
    entities = [
        MainGroupEntity::class,
        CategoryEntity::class,
        DescriptionEntity::class,
        RecipeEntity::class,
        MobsEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converter::class)
abstract class CraftMasterDataBase : RoomDatabase() {
    abstract fun mainGroupDao() : MainGroupDao
    abstract fun categoryDao(): CategoryDao
    abstract fun descriptionDao(): DescriptionDao
    abstract fun recipeDao(): RecipesDao
    abstract fun mobsDao(): MobsDao
}
