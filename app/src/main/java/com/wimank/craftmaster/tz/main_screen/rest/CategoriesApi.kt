package com.wimank.craftmaster.tz.main_screen.rest

import com.wimank.craftmaster.tz.main_screen.rest.response.CategoriesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CategoriesApi {

    @GET(value = "/minecraft/categories")
    fun getCategories(): Single<CategoriesResponse>

}