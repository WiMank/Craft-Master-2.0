package com.wimank.craftmaster.tz.recipe_screen.rest

import io.reactivex.Single
import retrofit2.http.GET

interface RecipeApi {

    @GET("/minecraft/recipes/recipe_craft")
    fun getRecipes(): Single<RecipeResponse>

}