package com.wimank.craftmaster.tz.common.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "data_base_version")
data class DbVersEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "db_id")
    var dbId: Int,

    @ColumnInfo(name = "version_db")
    var versionDb: Int
)