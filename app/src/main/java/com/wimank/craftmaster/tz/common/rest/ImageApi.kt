package com.wimank.craftmaster.tz.common.rest

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageApi {

    @GET("/image/{imageName}")
    fun downloadImage(@Path("imageName") imageName: String): Call<ResponseBody>

}