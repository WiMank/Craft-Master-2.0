package com.wimank.craftmaster.tz.main_screen.mvp.models

import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.main_screen.rest.response.MainGroupResponse
import io.reactivex.Single

class MainGroupModel(private val mainGroupApi: MainGroupApi) {

    fun getMainGroup(): Single<MainGroupResponse> {
        return mainGroupApi.getGroupList()
    }

}