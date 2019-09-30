package com.wimank.craftmaster.tz.main_screen.rest.response

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.room.entities.CategoriesEntity

data class CategoriesResponse(

    @SerializedName("categoriesList")
    val categoriesList: List<CategoriesEntity>
)
