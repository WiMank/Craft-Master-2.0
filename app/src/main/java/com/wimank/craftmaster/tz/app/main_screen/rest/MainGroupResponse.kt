package com.wimank.craftmaster.tz.app.main_screen.rest

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.main_screen.room.MainGroupEntity
import com.wimank.craftmaster.tz.common.rest.Success

data class MainGroupResponse(

    @SerializedName("success")
    val success: Success,

    @SerializedName("groupList")
    val groupList: List<MainGroupEntity>
)
