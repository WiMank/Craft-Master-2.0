package com.wimank.craftmaster.tz.main_screen.rest

import com.wimank.craftmaster.tz.main_screen.rest.response.DbVersResponse
import com.wimank.craftmaster.tz.main_screen.rest.response.GroupListItemResponse
import com.wimank.craftmaster.tz.main_screen.rest.response.GroupsVersionResponse
import com.wimank.craftmaster.tz.main_screen.rest.response.MainGroupResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface MainGroupApi {

    @GET("/main_group")
    fun getGroupList(): Single<MainGroupResponse>

    @GET("/main_group/item/{group}")
    fun getGroupItem(@Path("group") group: String): Single<GroupListItemResponse>

    @GET("/main_group/image/{imageName}")
    fun getGroupImage(@Path("imageName") imageName: String): Single<ResponseBody>

    @GET("/version/groups_version")
    fun getGroupsVersion(): Single<GroupsVersionResponse>

    @GET("/version")
    fun geServerDbVersion(): Single<DbVersResponse>

}