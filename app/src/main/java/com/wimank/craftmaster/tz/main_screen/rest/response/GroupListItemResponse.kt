package com.wimank.craftmaster.tz.main_screen.rest.response

import com.wimank.craftmaster.tz.common.rest.Success
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity

data class GroupListItemResponse(
    val success: Success,
    val mainGroupEntity: MainGroupEntity
)