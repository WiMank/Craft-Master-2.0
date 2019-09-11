package com.wimank.craftmaster.tz.common.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [],
    exportSchema = false,
    version = 1
)
abstract class CraftMasterDataBase : RoomDatabase() {

}