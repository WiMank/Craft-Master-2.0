package com.wimank.craftmaster.tz.app.rest.api

import com.wimank.craftmaster.tz.app.rest.responses.BrewingResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface BrewingApi {

    @GET("/brewing")
    fun getVersBrewingImages(): Single<BrewingResponse>

    @GET("/brewing/{imageName}")
    fun downloadBrewingImage(
        @Path("imageName") imageName: String,
        @Header("accept-language") language: String
    ): Call<ResponseBody>

}
