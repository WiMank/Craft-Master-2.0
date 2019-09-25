package com.wimank.craftmaster.tz.common.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "data_base_version")
data class DbVersEntity(
    @PrimaryKey
    @ColumnInfo(name = "version_db")
    var versionDb: Int
)