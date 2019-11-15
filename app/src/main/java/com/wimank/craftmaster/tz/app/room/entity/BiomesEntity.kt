package com.wimank.craftmaster.tz.app.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType

@Entity(tableName = "biomes")
data class BiomesEntity(

    @PrimaryKey
    @ColumnInfo(name = "biome_image")
    @SerializedName("biomeImage")
    val biomeImage: String,

    @ColumnInfo(name = "biome_name")
    @SerializedName("biomeName")
    val biomeName: LocalizedType,

    @ColumnInfo(name = "biome_type")
    @SerializedName("biomeType")
    val biomeType: LocalizedType,

    @ColumnInfo(name = "biome_temperature")
    @SerializedName("biomeTemperature")
    val biomeTemperature: String,

    @ColumnInfo(name = "biome_description")
    @SerializedName("biomeDescription")
    val biomeDescription: LocalizedType,

    @ColumnInfo(name = "biome_wiki")
    @SerializedName("biomeWiki")
    val biomeWiki: String,

    @ColumnInfo(name = "vers")
    @SerializedName("vers")
    val vers: Int = 0
) : BaseEntity {
    override fun getVersion() = vers

    override fun getImage() = biomeImage
}
