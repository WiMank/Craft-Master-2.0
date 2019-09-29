package com.wimank.craftmaster.tz.main_screen.rest

import com.wimank.craftmaster.tz.main_screen.rest.response.MainGroupResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MainGroupApi {

    @GET("/main_group")
    fun getMainGroupList(): Single<MainGroupResponse>

    @GET("/main_group/image/{imageName}")
    fun getMainGroupImage(@Path("imageName") imageName: String): Call<ResponseBody>

}