package com.wimank.craftmaster.tz.main_screen.rest

import com.wimank.craftmaster.tz.main_screen.rest.response.CategoriesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface CategoriesApi {

    @GET
    fun getCategories(@Url url: String) : Single<CategoriesResponse>

}