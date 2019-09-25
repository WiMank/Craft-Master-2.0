package com.wimank.craftmaster.tz.common.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups_version")
data class GroupsVersionEntity(
    @PrimaryKey
    var group: String,
    var vers: Int
)