package com.wimank.craftmaster.tz.main_screen.rest.response

import com.google.gson.annotations.SerializedName

data class MainGroupResponse(
    @field:SerializedName("groupList")
    val groupList: List<GroupListItem>
)

data class GroupListItem(
    @field:SerializedName("group")
    val group: String,

    @field:SerializedName("endpoint")
    val endpoint: String,

    @field:SerializedName("groupImage")
    val groupImage: String,

    @field:SerializedName("orderGroup")
    val orderGroup: Int,

    @field:SerializedName("vers")
    val vers: Int
)
