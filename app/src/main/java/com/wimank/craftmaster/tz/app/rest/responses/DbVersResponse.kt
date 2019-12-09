package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.annotations.SerializedName

data class DbVersResponse(

    @SerializedName("success")
    val success: Success,
    @SerializedName("dbVers")
    val dbVers: Int

)
