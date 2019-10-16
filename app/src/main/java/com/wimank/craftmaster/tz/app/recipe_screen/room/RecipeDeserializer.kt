package com.wimank.craftmaster.tz.app.recipe_screen.room

import com.google.gson.*
import com.wimank.craftmaster.tz.app.recipe_screen.rest.*
import com.wimank.craftmaster.tz.common.rest.Success
import java.lang.reflect.Type

class RecipeDeserializer : JsonDeserializer<RecipeResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): RecipeResponse {
        val recipesList = "recipesList"
        return RecipeResponse(
            parseSuccess(json.asJsonObject.get("success").asJsonObject),
            parseMcDescriptionEntityList(json.asJsonObject.get(recipesList).asJsonArray),
            parseMcRecipeEntityList(json.asJsonObject.get(recipesList).asJsonArray)
        )
    }

    private fun parseMcDescriptionEntityList(jo: JsonArray): List<DescriptionEntity> {
        return jo.map { parseMcDescriptionEntity(it.asJsonObject) }
    }

    private fun parseMcRecipeEntityList(jo: JsonArray): List<RecipeEntity> {
        return jo.map { parseMcRecipeEntity(it.asJsonObject) }
    }

    private fun parseMcDescriptionEntity(jo: JsonObject): DescriptionEntity {
        val recipeName = "recipeName"
        val leftParameter = "leftParameter"
        val leftParameterText = "leftParameterText"
        val rightParameter = "rightParameter"
        val rightParameterText = "rightParameterText"
        val descriptionCraft = "descriptionCraft"
        return DescriptionEntity(
            jo.get("recipeAttr").asString,
            jo.get("recipeImageName").asString,
            jo.get("group").asString,
            RecipeName(
                jo.get(recipeName).asJsonObject.get("en").asString,
                jo.get(recipeName).asJsonObject.get("ru").asString
            ),
            LeftParameter(
                jo.get(leftParameter).asJsonObject?.get("en")?.asString ?: "",
                jo.get(leftParameter).asJsonObject?.get("ru")?.asString ?: ""
            ),
            LeftParameterText(
                jo.get(leftParameterText).asJsonObject?.get("en")?.asString ?: "",
                jo.get(leftParameterText).asJsonObject?.get("ru")?.asString ?: ""
            ),

            jo.get("leftParameterImage").asString,

            RightParameter(
                jo.get(rightParameter).asJsonObject?.get("en")?.asString ?: "",
                jo.get(rightParameter).asJsonObject?.get("ru")?.asString ?: ""
            ),
            RightParameterText(
                jo.get(rightParameterText).asJsonObject?.get("en")?.asString ?: "",
                jo.get(rightParameterText).asJsonObject?.get("ru")?.asString ?: ""
            ),
            DescriptionCraft(
                jo.get(descriptionCraft).asJsonObject?.get("en")?.asString ?: "",
                jo.get(descriptionCraft).asJsonObject?.get("ru")?.asString ?: ""
            ),
            jo.get("wikiLink").asString,
            jo.get("vers").asInt
        )
    }

    private fun parseMcRecipeEntity(jo: JsonObject): RecipeEntity {
        val recipe = "recipe"
        return RecipeEntity(
            jo.get(recipe).asJsonObject.get("recipeImageName").asString,
            jo.get(recipe).asJsonObject.get("recipeAttr").asString,
            jo.get(recipe).asJsonObject.get("group").asString,
            jo.get(recipe).asJsonObject.get("firstSlot").asString,
            jo.get(recipe).asJsonObject.get("secondSlot").asString,
            jo.get(recipe).asJsonObject.get("threeSlot").asString,
            jo.get(recipe).asJsonObject.get("fourthSlot").asString,
            jo.get(recipe).asJsonObject.get("fifthSlot").asString,
            jo.get(recipe).asJsonObject.get("sixthSlot").asString,
            jo.get(recipe).asJsonObject.get("seventhSlot").asString,
            jo.get(recipe).asJsonObject.get("eighthSlot").asString,
            jo.get(recipe).asJsonObject.get("ninthSlot").asString,
            jo.get(recipe).asJsonObject.get("vers").asInt
        )
    }

    private fun parseSuccess(successJson: JsonObject): Success {
        return Success(
            successJson.get("httpStatus").asInt,
            successJson.get("message").asString
        )
    }
}
