package com.wimank.craftmaster.tz.main_screen.mvp.models

import android.content.Context
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.main_screen.rest.response.MainGroupResponse
import io.reactivex.Single
import okhttp3.Response

class MainGroupManager(
    private val context: Context,
    private val mainGroupApi: MainGroupApi,
    private val mainGroupDataBaseManager: MainGroupDataBaseManager
) {

    fun getMainGroup(): Single<MainGroupResponse> {
        return mainGroupApi.getGroupList()
    }

    fun getMainGroupImage(imageName: String): Single<Response> {
        return mainGroupApi.getGroupImage(imageName)
    }

    fun writeResponse(mainGroupResponse: MainGroupResponse) {

    }



    fun writeImage(){

    }

}