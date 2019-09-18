package com.wimank.craftmaster.tz.main_screen.rest

import com.wimank.craftmaster.tz.main_screen.rest.response.MainGroupResponse
import io.reactivex.Single
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MainGroupApi {

    @GET("/main_group")
    fun getGroupList(): Single<MainGroupResponse>

    @GET("/main_group/{imageName}")
    fun getGroupImage(@Path("imageName") imageName: String): Single<Response>

}