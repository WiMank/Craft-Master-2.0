package com.wimank.craftmaster.tz.app.rest.api

import com.wimank.craftmaster.tz.app.rest.responses.RecipeResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RecipesApi {

    @GET("/recipes")
    fun getRecipes(): Single<RecipeResponse>

}
