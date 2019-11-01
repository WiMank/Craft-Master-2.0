package com.wimank.craftmaster.tz.app.room.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType

@Entity(tableName = "manufacturing_devices", primaryKeys = ["recipeAttr", "recipeImageName"])
data class DeviceEntity(

    @SerializedName("recipeImageName")
    val recipeImageName: String,

    @SerializedName("recipeAttr")
    val recipeAttr: String,

    @SerializedName("furnace")
    val furnace: String,

    @SerializedName("extractor")
    val extractor: String,

    @SerializedName("crusher")
    val crusher: String,

    @SerializedName("compressor")
    val compressor: String,

    @SerializedName("recycler")
    val recycler: String,

    @SerializedName("assemblyTable")
    @ColumnInfo(name = "assembly_table")
    val assemblyTable: String,

    @SerializedName("machineName")
    @ColumnInfo(name = "machine_name")
    val machineName: LocalizedType,

    @SerializedName("machine")
    val machine: String,

    @SerializedName("vers")
    val vers: Int

) : BaseEntity {
    override fun getVersion() = vers
    override fun getImage() = recipeImageName
}
