package com.wimank.craftmaster.tz.app.main_screen.rest

import io.reactivex.Single
import retrofit2.http.GET

interface MainGroupApi {

    @GET("/main_group")
    fun getMainGroupList(): Single<MainGroupResponse>
}
