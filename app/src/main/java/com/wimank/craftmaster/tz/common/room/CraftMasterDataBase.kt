package com.wimank.craftmaster.tz.common.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wimank.craftmaster.tz.common.room.dao.DbVersDao
import com.wimank.craftmaster.tz.common.room.dao.GroupsVersionDao
import com.wimank.craftmaster.tz.common.room.dao.MainGroupDao
import com.wimank.craftmaster.tz.common.room.entities.DbVersEntity
import com.wimank.craftmaster.tz.common.room.entities.GroupsVersionEntity
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity


@Database(
    entities = [
        MainGroupEntity::class,
        DbVersEntity::class,
        GroupsVersionEntity::class],
    exportSchema = false,
    version = 1
)
abstract class CraftMasterDataBase : RoomDatabase() {
    abstract fun mainGroupDao() : MainGroupDao
    abstract fun dbVersDaoDao(): DbVersDao
    abstract fun groupsVersionDao(): GroupsVersionDao
}