package com.wimank.craftmaster.tz.app.rest.api

import com.wimank.craftmaster.tz.app.rest.responses.AddInfoResponse
import io.reactivex.Single
import retrofit2.http.GET

interface AddInfoApi {

    @GET("/recipes/additional")
    fun getAddInfo(): Single<AddInfoResponse>

}
