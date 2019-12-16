package com.wimank.craftmaster.tz.app.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType

@Entity(tableName = "achievements")
data class AchievementEntity(

    @SerializedName("achName")
    @ColumnInfo(name = "ach_name")
    val achName: LocalizedType,

    @SerializedName("achDescription")
    @ColumnInfo(name = "ach_description")
    val achDescription: LocalizedType,

    @PrimaryKey
    @SerializedName("achImage")
    @ColumnInfo(name = "ach_image")
    val achImage: String,

    @SerializedName("achModification")
    @ColumnInfo(name = "ach_modification")
    val achModification: String,

    @SerializedName("vers")
    @ColumnInfo(name = "vers")
    val vers: Int

) : BaseEntity {

    override fun getVersion() = vers

    override fun getImage() = achImage
}
