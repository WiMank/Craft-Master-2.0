package com.wimank.craftmaster.tz.main_screen.rest.response

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.rest.Success
import com.wimank.craftmaster.tz.common.room.entities.CategoriesEntity

@JsonAdapter(CategoriesDeserializer::class)
data class CategoriesResponse(

    @SerializedName("success")
    var success: Success,

    @SerializedName("categoriesList")
    var categoriesList: List<CategoriesEntity>

)