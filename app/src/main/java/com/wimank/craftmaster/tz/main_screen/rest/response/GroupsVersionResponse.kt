package com.wimank.craftmaster.tz.main_screen.rest.response

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.room.entities.GroupsVersionEntity

data class GroupsVersionResponse (
    @SerializedName("groupVersionList")
    var groupVersionList: List<GroupsVersionEntity>
)