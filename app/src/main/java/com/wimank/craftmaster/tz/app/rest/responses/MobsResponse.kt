package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.room.entitys.MobsEntity
import com.wimank.craftmaster.tz.common.rest.Success

data class MobsResponse(

    @SerializedName("success")
    val success: Success,

    @SerializedName("mobsListEntity")
    val mobsLost: List<MobsEntity>
)
