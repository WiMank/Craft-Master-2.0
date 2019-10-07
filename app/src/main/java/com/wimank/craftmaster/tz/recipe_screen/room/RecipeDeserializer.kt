package com.wimank.craftmaster.tz.recipe_screen.room

import com.google.gson.*
import com.wimank.craftmaster.tz.common.rest.Success
import com.wimank.craftmaster.tz.recipe_screen.rest.DescriptionCraft
import com.wimank.craftmaster.tz.recipe_screen.rest.LleftParameter
import com.wimank.craftmaster.tz.recipe_screen.rest.RecipeName
import com.wimank.craftmaster.tz.recipe_screen.rest.RecipeResponse
import java.lang.reflect.Type

class RecipeDeserializer : JsonDeserializer<RecipeResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): RecipeResponse {
        return RecipeResponse(
            parseSuccess(json.asJsonObject.get("success").asJsonObject),
            parseMcDescriptionEntityList(json.asJsonObject.get("recipesList").asJsonArray),
            parseMcRecipeEntityList(json.asJsonObject.get("recipesList").asJsonArray)
        )
    }

    private fun parseMcDescriptionEntityList(jo: JsonArray): List<DescriptionEntity> {
        return jo.map { parseMcDescriptionEntity(it.asJsonObject) }
    }

    private fun parseMcRecipeEntityList(jo: JsonArray): List<RecipeEntity> {
        return jo.map { parseMcRecipeEntity(it.asJsonObject) }
    }

    private fun parseMcDescriptionEntity(jo: JsonObject): DescriptionEntity {
        return DescriptionEntity(
            jo.get("recipeAttr").asString,
            jo.get("recipeImageName").asString,
            RecipeName(
                jo.get("recipe_name").asJsonObject.get("en").asString,
                jo.get("recipe_name").asJsonObject.get("ru").asString
            ),
            LleftParameter(
                if (jo.get("lleftParameter").asJsonObject.get("en") != null)
                    jo.get("lleftParameter").asJsonObject.get("en").asString
                else
                    "",

                if (jo.get("lleftParameter").asJsonObject.get("ru") != null)
                    jo.get("lleftParameter").asJsonObject.get("ru").asString
                else
                    ""
            ),
            jo.get("lleftParameterImage").asString,
            jo.get("rrightParameter").asString,
            DescriptionCraft(
                jo.get("descriptionCraft").asJsonObject.get("en").asString,
                jo.get("descriptionCraft").asJsonObject.get("ru").asString
            ),
            jo.get("wikiLink").asString,
            jo.get("vers").asInt
        )
    }

    private fun parseMcRecipeEntity(jo: JsonObject): RecipeEntity {
        return RecipeEntity(
            jo.get("recipe").asJsonObject.get("recipeImageName").asString,
            jo.get("recipe").asJsonObject.get("recipeAttr").asString,
            jo.get("recipe").asJsonObject.get("firstSlot").asString,
            jo.get("recipe").asJsonObject.get("secondSlot").asString,
            jo.get("recipe").asJsonObject.get("threeSlot").asString,
            jo.get("recipe").asJsonObject.get("fourthSlot").asString,
            jo.get("recipe").asJsonObject.get("fifthSlot").asString,
            jo.get("recipe").asJsonObject.get("sixthSlot").asString,
            jo.get("recipe").asJsonObject.get("seventhSlot").asString,
            jo.get("recipe").asJsonObject.get("eighthSlot").asString,
            jo.get("recipe").asJsonObject.get("ninthSlot").asString,
            jo.get("recipe").asJsonObject.get("vers").asInt
        )
    }

    private fun parseSuccess(successJson: JsonObject): Success {
        return Success(
            successJson.get("httpStatus").asInt,
            successJson.get("message").asString
        )
    }
}