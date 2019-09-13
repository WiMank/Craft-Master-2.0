package com.wimank.craftmaster.tz.common.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity


@Database(
    entities = [MainGroupEntity::class],
    exportSchema = false,
    version = 1
)
abstract class CraftMasterDataBase : RoomDatabase() {

}