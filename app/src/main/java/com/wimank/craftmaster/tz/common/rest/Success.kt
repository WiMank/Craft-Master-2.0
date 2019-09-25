package com.wimank.craftmaster.tz.common.rest

import com.google.gson.annotations.SerializedName

class Success(
    @SerializedName("httpStatus")
    var httpStatus: Int,
    @SerializedName("message")
    var message: String
) {
    fun isSuccess(): Boolean {
        return httpStatus == 200
    }
}