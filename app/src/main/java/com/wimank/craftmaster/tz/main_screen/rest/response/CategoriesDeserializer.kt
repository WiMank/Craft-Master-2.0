package com.wimank.craftmaster.tz.main_screen.rest.response

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.wimank.craftmaster.tz.common.room.entities.CategoriesEntity
import com.wimank.craftmaster.tz.common.room.entities.Category
import java.lang.reflect.Type

class CategoriesDeserializer : JsonDeserializer<CategoriesEntity> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): CategoriesEntity {
        val jObject = json.asJsonObject
        return parseCategoriesEntity(jObject.get("categoriesList").asJsonObject)
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
}

