package com.wimank.craftmaster.tz.common.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey


@Entity(tableName = "main_group")
data class MainGroupEntity(

    @PrimaryKey
    val group: String,

    @ColumnInfo(name = "group_image")
    val groupImage: String,

    val endpoint: String,

    @ColumnInfo(name = "order_group")
    val orderGroup: Int

)