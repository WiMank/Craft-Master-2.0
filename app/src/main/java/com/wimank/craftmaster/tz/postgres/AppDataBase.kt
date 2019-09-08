package com.wimank.craftmaster.tz.postgres

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wimank.craftmaster.tz.postgres.RecipesDAO
import com.wimank.craftmaster.tz.postgres.RecipesEntity


@Database(
    entities = [RecipesEntity::class],
    version = 1,
    exportSchema = true
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun recipesDAO(): RecipesDAO
}