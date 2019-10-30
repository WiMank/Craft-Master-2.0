package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.annotations.SerializedName

data class ManufacturingDResponse(

    @SerializedName("recipeAttr")
    val recipeAttr: String,

    @SerializedName("recipeImageName")
    val recipeImageName: String,

    @SerializedName("furnace")
    val furnace: String,

    @SerializedName("extractor")
    val extractor: String,

    @SerializedName("crusher")
    val crusher: String?,

    @SerializedName("compressor")
    val compressor: String,

    @SerializedName("recycler")
    val recycler: String,

    @SerializedName("assembly_table")
    val assemblyTable: String,

    @SerializedName("machine")
    val machine: String,

    @SerializedName("machine_name")
    val machineName: LocalizedType,

    @SerializedName("vers")
    val vers: Int
)