package com.wimank.craftmaster.tz.common.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wimank.craftmaster.tz.app.room.dao.CategoryDao
import com.wimank.craftmaster.tz.app.room.dao.DescriptionDao
import com.wimank.craftmaster.tz.app.room.dao.MobsDao
import com.wimank.craftmaster.tz.app.room.dao.RecipesDao
import com.wimank.craftmaster.tz.app.room.entitys.CategoryEntity
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entitys.MobsEntity
import com.wimank.craftmaster.tz.app.room.entitys.RecipeEntity

@Database(
    entities = [
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
    abstract fun categoryDao(): CategoryDao
    abstract fun descriptionDao(): DescriptionDao
    abstract fun recipeDao(): RecipesDao
    abstract fun mobsDao(): MobsDao
}
