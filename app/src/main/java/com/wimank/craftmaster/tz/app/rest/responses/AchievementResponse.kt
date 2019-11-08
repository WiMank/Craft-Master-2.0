package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.room.entitys.AchievementEntity

data class AchievementResponse(
    @SerializedName("success")
    val success: Success,

    @SerializedName("list")
    val list: List<AchievementEntity>
)
