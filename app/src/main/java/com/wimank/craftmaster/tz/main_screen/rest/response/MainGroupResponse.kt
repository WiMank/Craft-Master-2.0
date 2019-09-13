package com.wimank.craftmaster.tz.main_screen.rest.response

data class MainGroupResponse(
    val groupList: List<GroupListItem>
)

class GroupListItem(
    val endpoint: String,
    val groupImage: ByteArray,
    val group: String
)
