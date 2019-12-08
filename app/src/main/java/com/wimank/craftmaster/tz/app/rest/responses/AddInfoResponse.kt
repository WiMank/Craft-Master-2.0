package com.wimank.craftmaster.tz.app.rest.responses

import com.google.gson.annotations.JsonAdapter
import com.wimank.craftmaster.tz.app.room.entity.AdditionalEntity

@JsonAdapter(AddInfoDeserializer::class)
data class AddInfoResponse(

    val success: Success,
    val additionalInfo: List<AdditionalEntity>

)
