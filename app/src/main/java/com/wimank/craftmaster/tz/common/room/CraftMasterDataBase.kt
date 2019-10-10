package com.wimank.craftmaster.tz.common.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wimank.craftmaster.tz.categories_screen.room.CategoryDao
import com.wimank.craftmaster.tz.categories_screen.room.CategoryEntity
import com.wimank.craftmaster.tz.main_screen.room.MainGroupDao
import com.wimank.craftmaster.tz.main_screen.room.MainGroupEntity
import com.wimank.craftmaster.tz.recipe_screen.room.DescriptionDao
import com.wimank.craftmaster.tz.recipe_screen.room.DescriptionEntity
import com.wimank.craftmaster.tz.recipe_screen.room.RecipeEntity
import com.wimank.craftmaster.tz.recipe_screen.room.RecipesDao

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
