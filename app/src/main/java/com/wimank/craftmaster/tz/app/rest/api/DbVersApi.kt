package com.wimank.craftmaster.tz.app.rest.api

import com.wimank.craftmaster.tz.app.rest.responses.DbVersResponse
import io.reactivex.Single
import retrofit2.http.GET

interface DbVersApi {

    @GET("/db_version")
    fun getDbVers(): Single<DbVersResponse>

}
