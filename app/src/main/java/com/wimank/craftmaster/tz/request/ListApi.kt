package com.wimank.craftmaster.tz.request

import com.wimank.craftmaster.tz.postgres.CraftDescEntity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ListApi {

    @POST("/recipes/minecraft_recipes")
    @Headers("Accept: application/json", "Content-Type: application/json")
    fun postList(@Body requestList: RequestList): Call<ResponseBody>


    @POST("/minecraft_recipe/craft")
    @Headers("Accept: application/json", "Content-Type: application/json")
    fun postCraft(@Body craftDescEntity: CraftDescEntity): Call<ResponseBody>

}