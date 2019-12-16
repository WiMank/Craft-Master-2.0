package com.wimank.craftmaster.tz.app.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "brewing")

data class BrewingEntity(

    @PrimaryKey
    @ColumnInfo(name = "brewing_image")
    @SerializedName("brewingImage")
    val brewingImage: String,

    @ColumnInfo(name = "vers")
    @SerializedName("vers")
    val vers: Int

) : BaseEntity {

    override fun getVersion() = vers

    override fun getImage() = brewingImage
}
