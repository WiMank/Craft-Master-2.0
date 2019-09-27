package com.wimank.craftmaster.tz.common.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "groups_version")
data class GroupsVersionEntity(
    @PrimaryKey
    @SerializedName("group")
    var group: String,

    @SerializedName("vers")
    var vers: Int
)