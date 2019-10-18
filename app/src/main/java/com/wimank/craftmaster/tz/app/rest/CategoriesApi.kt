package com.wimank.craftmaster.tz.app.rest

import com.wimank.craftmaster.tz.app.room.entitys.CategoryEntity
import io.reactivex.Single
import retrofit2.http.GET

interface CategoriesApi {

    @GET(value = "/categories")
    fun getMcCategory(): Single<CategoryResponse<CategoryEntity>>

}
