package com.wimank.craftmaster.tz.app.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wimank.craftmaster.tz.app.room.dao.*
import com.wimank.craftmaster.tz.app.room.entitys.*

@Database(
    entities = [
        DescriptionEntity::class,
        RecipeEntity::class,
        MobsEntity::class,
        DeviceEntity::class,
        AchievementEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converter::class)
abstract class CraftMasterDataBase : RoomDatabase() {
    abstract fun descriptionDao(): DescriptionDao
    abstract fun recipeDao(): RecipesDao
    abstract fun mobsDao(): MobsDao
    abstract fun devicesDao(): DevicesDao
    abstract fun achievementDao(): AchievementDao
}
