package com.wimank.craftmaster.tz.main_screen.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.room.BaseEntity


@Entity(tableName = "main_group")
data class MainGroupEntity(

    @PrimaryKey
    @SerializedName("group")
    var group: String,

    @ColumnInfo(name = "group_image")
    @SerializedName("groupImage")
    var groupImage: String,

    @ColumnInfo(name = "order_group")
    @SerializedName("orderGroup")
    var orderGroup: Int,

    @ColumnInfo(name = "vers")
    @SerializedName("vers")
    var vers: Int

) : BaseEntity {
    override fun getImage(): String = groupImage
    override fun getVersion(): Int = vers
}