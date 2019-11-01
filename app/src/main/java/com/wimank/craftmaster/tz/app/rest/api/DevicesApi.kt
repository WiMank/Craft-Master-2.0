package com.wimank.craftmaster.tz.app.rest.api

import com.wimank.craftmaster.tz.app.rest.responses.DevicesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface DevicesApi {

    @GET("recipes/manufacturing_d")
    fun getDevices(): Single<DevicesResponse>

}
