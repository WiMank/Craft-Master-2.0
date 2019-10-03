package com.wimank.craftmaster.tz.recipe_screen.rest

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.rest.Success

data class RecipeResponse(

    @SerializedName("success")
    var success: Success,

    @SerializedName("recipesList")
    var recipesList: List<RecipesListItem>
)

data class RecipesListItem(

    @SerializedName("lleftParameter")
    var lleftParameter: LleftParameter,

    @SerializedName("lleftParameterImage")
    var lleftParameterImage: String,

    @SerializedName("recipeImageName")
    var recipeImageName: String,

    @SerializedName("wikiLink")
    var wikiLink: String,

    @SerializedName("recipe")
    var recipe: Recipe,

    @SerializedName("recipe_name")
    var recipeName: RecipeName,

    @SerializedName("recipeAttr")
    var recipeAttr: String,

    @SerializedName("descriptionCraft")
    var descriptionCraft: DescriptionCraft,

    @SerializedName("rrightParameter")
    var rrightParameter: String
)

data class LleftParameter(
    @SerializedName("en")
    var en: String,

    @SerializedName("ru")
    var ru: String
)

data class RecipeName(

    @SerializedName("ru")
    var ru: String,

    @SerializedName("en")
    var en: String
)

data class Recipe(

    @SerializedName("secondSlot")
    var secondSlot: String,

    @SerializedName("firstSlot")
    var firstSlot: String,

    @SerializedName("ninthSlot")
    var ninthSlot: String,

    @SerializedName("seventhSlot")
    var seventhSlot: String,

    @SerializedName("eighthSlot")
    var eighthSlot: String,

    @SerializedName("fourthSlot")
    var fourthSlot: String,

    @SerializedName("fifthSlot")
    var fifthSlot: String,

    @SerializedName("sixthSlot")
    var sixthSlot: String,

    @SerializedName("threeSlot")
    var threeSlot: String
)

data class DescriptionCraft(

    @SerializedName("ru")
    var ru: String,

    @SerializedName("en")
    var en: String
)