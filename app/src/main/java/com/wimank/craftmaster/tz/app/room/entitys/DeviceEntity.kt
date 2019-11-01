package com.wimank.craftmaster.tz.app.room.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType

@Entity(tableName = "manufacturing_devices", primaryKeys = ["recipeAttr", "recipeImageName"])
data class DeviceEntity(

    @SerializedName("recipeAttr")
    val recipeAttr: String,

    @SerializedName("recipeImageName")
    val recipeImageName: String,

    @SerializedName("furnace")
    val furnace: LocalizedType,

    @SerializedName("extractor")
    val extractor: LocalizedType,

    @SerializedName("crusher")
    val crusher: LocalizedType,

    @SerializedName("compressor")
    val compressor: LocalizedType,

    @SerializedName("recycler")
    val recycler: LocalizedType,

    @SerializedName("assemblyTable")
    @ColumnInfo(name = "assembly_table")
    val assemblyTable: LocalizedType,

    @SerializedName("machine")
    val machine: String,

    @SerializedName("machineName")
    @ColumnInfo(name = "machine_name")
    val machineName: LocalizedType,

    @SerializedName("vers")
    val vers: Int

) : BaseEntity {
    override fun getVersion() = vers
    override fun getImage() = machine
}
