package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.room.entity.BrewingEntity

data class BrewingResponse(

    @SerializedName("success")
    val success: Success,

    @SerializedName("images")
    val images: List<BrewingEntity>
)
