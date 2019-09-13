package com.wimank.craftmaster.tz.common.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "main_group")
class MainGroupEntity(

    @PrimaryKey
    val group: String,

    @ColumnInfo(name = "group_image")
    val groupImage: ByteArray,

    val endpoint: String

)