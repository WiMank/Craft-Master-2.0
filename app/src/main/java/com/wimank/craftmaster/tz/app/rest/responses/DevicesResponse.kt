package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.room.entitys.DeviceEntity

@JsonAdapter(DevicesDeserializer::class)
data class DevicesResponse(

    @SerializedName("success")
    val success: Success,

    @SerializedName("manufacturingDevicesList")
    val devices: List<DeviceEntity>
)
