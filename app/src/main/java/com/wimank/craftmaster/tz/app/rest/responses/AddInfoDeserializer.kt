package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.*
import com.wimank.craftmaster.tz.app.room.entity.AdditionalEntity
import java.lang.reflect.Type

class AddInfoDeserializer : JsonDeserializer<AddInfoResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext?
    ): AddInfoResponse {
        return AddInfoResponse(
            parseSuccess(json.asJsonObject.get("success").asJsonObject),
            parseDevices(json.asJsonObject.get("additionalInfo").asJsonArray)
        )
    }

    private fun parseDevices(jo: JsonArray): List<AdditionalEntity> {
        return jo.map { parseAdditionalEntity(it.asJsonObject) }
    }

    private fun parseAdditionalEntity(jo: JsonObject): AdditionalEntity {
        val recipePrimaryKey = "recipePrimaryKey"
        val leftPr = "leftPr"
        val rightPr = "rightPr"

        return AdditionalEntity(
            jo.get(recipePrimaryKey).asJsonObject.get("recipeAttr").asString,
            jo.get(recipePrimaryKey).asJsonObject.get("recipeImageName").asString,
            jo.get("attackDamage").asString,
            jo.get("durability").asString,
            jo.get("armor").asString,
            jo.get("restores").asString,
            LocalizedType(
                jo.get(leftPr).asJsonObject?.get("en")?.asString ?: "",
                jo.get(leftPr).asJsonObject?.get("ru")?.asString ?: ""
            ),
            LocalizedType(
                jo.get(rightPr).asJsonObject?.get("en")?.asString ?: "",
                jo.get(rightPr).asJsonObject?.get("ru")?.asString ?: ""
            ),
            jo.get("smallImage").asString,
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
