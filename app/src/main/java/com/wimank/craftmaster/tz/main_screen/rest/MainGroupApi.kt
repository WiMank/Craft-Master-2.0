package com.wimank.craftmaster.tz.main_screen.rest

import com.wimank.craftmaster.tz.main_screen.rest.response.MainGroupResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MainGroupApi {

    @GET("/main_group")
    fun getGroupList(): Single<MainGroupResponse>

}