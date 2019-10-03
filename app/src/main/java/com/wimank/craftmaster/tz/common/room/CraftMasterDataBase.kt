package com.wimank.craftmaster.tz.common.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wimank.craftmaster.tz.common.room.dao.*
import com.wimank.craftmaster.tz.common.room.entities.*
import com.wimank.craftmaster.tz.recipe_screen.room.McDescriptionEntity
import com.wimank.craftmaster.tz.recipe_screen.room.McRecipeEntity


@Database(
    entities = [
        MainGroupEntity::class,
        McCategoryEntity::class,
        IcCategoryEntity::class,
        BcCategoryEntity::class,
        FrCategoryEntity::class,
        McRecipesListEntity::class,
        McDescriptionEntity::class,
        McRecipeEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class CraftMasterDataBase : RoomDatabase() {
    abstract fun mainGroupDao() : MainGroupDao
    abstract fun mcCategoryDao(): McCategoryDao
    abstract fun bcCategoryDao(): BcCategoryDao
    abstract fun icCategoryDao(): IcCategoryDao
    abstract fun frCategoryDao(): FrCategoryDao
    abstract fun mcRecipesListDao(): McRecipesListDao
}