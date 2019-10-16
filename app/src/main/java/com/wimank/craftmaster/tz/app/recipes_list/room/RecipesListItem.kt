package com.wimank.craftmaster.tz.app.recipes_list.room

import com.wimank.craftmaster.tz.app.recipe_screen.rest.RecipeName

data class RecipesListItem(
    val recipeName: RecipeName,
    val recipeImageName: String,
    val recipeAttr: String
)
