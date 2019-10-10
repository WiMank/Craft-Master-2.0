package com.wimank.craftmaster.tz.common.rest

import com.google.gson.annotations.SerializedName

class Success(
    @SerializedName("httpStatus")
    val httpStatus: Int,
    @SerializedName("message")
    val message: String
) {
    fun isSuccess(): Boolean {
        return httpStatus == 200
    }
}
