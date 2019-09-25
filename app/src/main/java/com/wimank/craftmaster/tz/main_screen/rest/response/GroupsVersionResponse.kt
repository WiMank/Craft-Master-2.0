package com.wimank.craftmaster.tz.main_screen.rest.response

import com.wimank.craftmaster.tz.common.room.entities.GroupsVersionEntity

data class GroupsVersionResponse (
    var groupVersionList: List<GroupsVersionEntity>
)