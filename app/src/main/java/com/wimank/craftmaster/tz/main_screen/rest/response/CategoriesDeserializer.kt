package com.wimank.craftmaster.tz.main_screen.rest.response

import com.google.gson.*
import com.wimank.craftmaster.tz.common.rest.Success
import com.wimank.craftmaster.tz.common.room.entities.BaseEntity
import com.wimank.craftmaster.tz.common.room.entities.Category
import com.wimank.craftmaster.tz.common.room.entities.CategoryEntity
import com.wimank.craftmaster.tz.common.room.entities.McCategoryEntity
import java.lang.reflect.Type

class CategoriesDeserializer : JsonDeserializer<CategoriesResponse<BaseEntity>> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): CategoriesResponse<BaseEntity> {
        val jObject = json.asJsonObject
        return CategoriesResponse(
            parseSuccess(jObject.get("success").asJsonObject),
            parseCategoriesList(jObject.get("mcCategoryList").asJsonArray)
        )
    }

    private fun parseCategoriesList(jo: JsonArray): List<BaseEntity> {
        return jo.map {
            parseCategoriesEntity(it.asJsonObject)
        }
    }

    private fun parseCategoriesEntity(jo: JsonObject): BaseEntity {
        return McCategoryEntity(
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

