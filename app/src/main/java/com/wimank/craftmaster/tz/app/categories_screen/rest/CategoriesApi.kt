package com.wimank.craftmaster.tz.app.categories_screen.rest

import com.wimank.craftmaster.tz.app.categories_screen.room.CategoryEntity
import io.reactivex.Single
import retrofit2.http.GET

interface CategoriesApi {

    @GET(value = "/categories")
    fun getMcCategory(): Single<CategoryResponse<CategoryEntity>>

}
