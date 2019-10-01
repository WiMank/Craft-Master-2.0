package com.wimank.craftmaster.tz.main_screen.rest.response

import com.google.gson.*
import com.wimank.craftmaster.tz.common.rest.Success
import com.wimank.craftmaster.tz.common.room.entities.CategoriesEntity
import com.wimank.craftmaster.tz.common.room.entities.Category
import java.lang.reflect.Type

class CategoriesDeserializer : JsonDeserializer<CategoriesResponse> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): CategoriesResponse {
        val jObject = json.asJsonObject
        return CategoriesResponse(
            parseSuccess(jObject.get("success").asJsonObject),
            parseCategoriesList(jObject.get("categoriesList").asJsonArray)
        )
    }

    private fun parseCategoriesList(jo: JsonArray): List<CategoriesEntity> {
        val l = arrayListOf<CategoriesEntity>()
        jo.forEach {
            l.add(parseCategoriesEntity(it.asJsonObject))
        }
        return l
    }

    private fun parseCategoriesEntity(jo: JsonObject): CategoriesEntity {
        return CategoriesEntity(
            jo.get("categoriesPrKey").asJsonObject.get("group").asString,
            jo.get("categoriesPrKey").asJsonObject.get("orderCategories").asInt,
            Category(
                jo.get("category").asJsonObject.get("en").asString,
                jo.get("category").asJsonObject.get("ru").asString
            ),
            jo.get("categoryImage").asString,
            jo.get("categoryEndpoint").asString,
            jo.get("vers").asInt
        )
    }

    private fun parseSuccess(successJson: JsonObject): Success {
        return Success(
            successJson.get("httpStatus").asInt,
            successJson.get("message").asString
        )
    }
}

