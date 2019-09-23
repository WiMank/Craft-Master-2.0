package com.wimank.craftmaster.tz.main_screen.rest.response

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity

data class MainGroupResponse(
    @SerializedName("groupList")
    val groupList: List<MainGroupEntity>
)