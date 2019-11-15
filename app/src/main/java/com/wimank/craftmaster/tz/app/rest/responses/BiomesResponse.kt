package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.room.entity.BiomesEntity

data class BiomesResponse(
    @SerializedName("success")
    val success: Success,

    @SerializedName("biomesList")
    val biomesList: List<BiomesEntity>
)
