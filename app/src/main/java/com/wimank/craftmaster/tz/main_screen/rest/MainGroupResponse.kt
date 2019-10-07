package com.wimank.craftmaster.tz.main_screen.rest

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.rest.Success
import com.wimank.craftmaster.tz.main_screen.room.MainGroupEntity

data class MainGroupResponse(

    @SerializedName("success")
    var success: Success,

    @SerializedName("groupList")
    val groupList: List<MainGroupEntity>
)