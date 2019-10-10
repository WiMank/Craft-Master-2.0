package com.wimank.craftmaster.tz.app.recipe_screen.rest

import io.reactivex.Single
import retrofit2.http.GET

interface RecipesApi {

    @GET("/recipes/recipe_craft")
    fun getRecipes(): Single<RecipeResponse>

}
