package com.wimank.craftmaster.tz.main_screen.rest

import com.wimank.craftmaster.tz.common.room.entities.McCategoryEntity
import com.wimank.craftmaster.tz.main_screen.rest.response.CategoryResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CategoriesApi {

    @GET(value = "/minecraft/categories")
    fun getMcCategory(): Single<CategoryResponse<McCategoryEntity>>

}