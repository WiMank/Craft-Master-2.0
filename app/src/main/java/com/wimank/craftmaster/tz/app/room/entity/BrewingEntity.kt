package com.wimank.craftmaster.tz.app.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brewing")

data class BrewingEntity(

    @PrimaryKey
    @ColumnInfo(name = "brewing_image")
    val brewingImage: String,

    @ColumnInfo(name = "vers")
    val vers: Int
)
