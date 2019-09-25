package com.wimank.craftmaster.tz.main_screen.rest.response

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.rest.Success

data class DbVersResponse(
    @SerializedName("success")
    var success: Success,
    @SerializedName("versionDb")
    var versionDb: Int
)