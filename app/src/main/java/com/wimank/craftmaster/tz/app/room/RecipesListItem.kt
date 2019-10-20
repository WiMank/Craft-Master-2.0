package com.wimank.craftmaster.tz.app.room

import com.wimank.craftmaster.tz.app.rest.responses.RecipeName

data class RecipesListItem(
    val recipeName: RecipeName,
    val recipeImageName: String,
    val recipeAttr: String
)
