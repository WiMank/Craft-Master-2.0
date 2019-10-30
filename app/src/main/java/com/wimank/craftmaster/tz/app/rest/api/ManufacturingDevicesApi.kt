package com.wimank.craftmaster.tz.app.rest.api

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ManufacturingDevicesApi {

    @GET("recipes/manufacturing_d")
    fun getManufacturingDevices(): Single<ResponseBody>

}
