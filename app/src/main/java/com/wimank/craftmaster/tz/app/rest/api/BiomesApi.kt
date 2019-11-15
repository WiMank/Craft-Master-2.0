package com.wimank.craftmaster.tz.app.rest.api

import com.wimank.craftmaster.tz.app.rest.responses.BiomesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BiomesApi {

    @GET("/biomes")
    fun getBiomes(): Single<BiomesResponse>

}
