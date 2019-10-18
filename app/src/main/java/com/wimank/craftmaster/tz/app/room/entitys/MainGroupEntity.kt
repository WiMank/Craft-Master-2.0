package com.wimank.craftmaster.tz.app.room.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.room.BaseEntity

@Entity(tableName = "main_group")
data class MainGroupEntity(

    @PrimaryKey
    @SerializedName("group")
    val group: String,

    @ColumnInfo(name = "group_image")
    @SerializedName("groupImage")
    val groupImage: String,

    @ColumnInfo(name = "order_group")
    @SerializedName("orderGroup")
    val orderGroup: Int,

    @ColumnInfo(name = "vers")
    @SerializedName("vers")
    val vers: Int

) : BaseEntity {
    override fun getImage(): String = groupImage
    override fun getVersion(): Int = vers
}
