package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.annotations.SerializedName

class Success(
    @SerializedName("httpStatus")
    val httpStatus: Int,
    @SerializedName("message")
    val message: String
) {
    fun isSuccess() = httpStatus == 200
}
