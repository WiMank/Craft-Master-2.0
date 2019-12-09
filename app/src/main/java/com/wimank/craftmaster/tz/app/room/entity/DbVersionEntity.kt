package com.wimank.craftmaster.tz.app.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "db_vers")
data class DbVersionEntity(

    @PrimaryKey
    @ColumnInfo(name = "db_version")
    val dbVersion: Int

)
