package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.*
import com.wimank.craftmaster.tz.app.room.entitys.DeviceEntity
import java.lang.reflect.Type

class DevicesDeserializer : JsonDeserializer<DevicesResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): DevicesResponse {
        return DevicesResponse(
            parseSuccess(json.asJsonObject.get("success").asJsonObject),
            parseDevices(json.asJsonObject.get("manufacturingDevicesList").asJsonArray)
        )
    }

    private fun parseDevices(jo: JsonArray): List<DeviceEntity> {
        return jo.map { parseManufacturingDevicesEntity(it.asJsonObject) }
    }

    private fun parseManufacturingDevicesEntity(jo: JsonObject): DeviceEntity {
        val machineName = "machineName"
        val recipePrimaryKey = "recipePrimaryKey"
        return DeviceEntity(
            jo.get(recipePrimaryKey).asJsonObject.get("recipeAttr").asString,
            jo.get(recipePrimaryKey).asJsonObject.get("recipeImageName").asString,
            LocalizedType(
                jo.get(machineName).asJsonObject?.get("en")?.asString ?: "",
                jo.get(machineName).asJsonObject?.get("ru")?.asString ?: ""
            ),
            LocalizedType(
                jo.get(machineName).asJsonObject?.get("en")?.asString ?: "",
                jo.get(machineName).asJsonObject?.get("ru")?.asString ?: ""
            ),
            LocalizedType(
                jo.get(machineName).asJsonObject?.get("en")?.asString ?: "",
                jo.get(machineName).asJsonObject?.get("ru")?.asString ?: ""
            ),
            LocalizedType(
                jo.get(machineName).asJsonObject?.get("en")?.asString ?: "",
                jo.get(machineName).asJsonObject?.get("ru")?.asString ?: ""
            ),
            LocalizedType(
                jo.get(machineName).asJsonObject?.get("en")?.asString ?: "",
                jo.get(machineName).asJsonObject?.get("ru")?.asString ?: ""
            ),
            LocalizedType(
                jo.get(machineName).asJsonObject?.get("en")?.asString ?: "",
                jo.get(machineName).asJsonObject?.get("ru")?.asString ?: ""
            ),
            LocalizedType(
                jo.get(machineName).asJsonObject?.get("en")?.asString ?: "",
                jo.get(machineName).asJsonObject?.get("ru")?.asString ?: ""
            ),
            jo.get("machine").asString,
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
