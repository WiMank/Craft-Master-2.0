package com.wimank.craftmaster.tz.app.rest.api

import com.wimank.craftmaster.tz.app.rest.responses.MobsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MobsApi {

    @GET("/mobs")
    fun getMobs(): Single<MobsResponse>
}
