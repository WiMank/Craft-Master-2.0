package com.wimank.craftmaster.tz.app.rest.api

import com.wimank.craftmaster.tz.app.rest.responses.ManufacturingDResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ManufacturingDevicesApi {

    @GET("recipes/manufacturing_d")
    fun getManufacturingDevices(): Single<ManufacturingDResponse>

}
