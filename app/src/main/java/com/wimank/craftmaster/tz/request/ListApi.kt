package com.wimank.craftmaster.tz.request

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ListApi {

    @POST("/recipes/minecraft_recipes")
    @Headers("Accept: application/json", "Content-Type: application/json")
    fun postList(@Body requestList: RequestList): Call<ResponseBody>

}