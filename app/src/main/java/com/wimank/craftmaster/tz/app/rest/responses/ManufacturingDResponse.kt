package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.room.entitys.ManufacturingDevicesEntity

@JsonAdapter(DevicesDeserializer::class)
data class ManufacturingDResponse(

    @SerializedName("success")
    val success: Success,

    @SerializedName("manufacturingDevicesList")
    val devices: List<ManufacturingDevicesEntity>
)
