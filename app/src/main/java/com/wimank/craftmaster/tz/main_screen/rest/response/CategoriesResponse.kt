package com.wimank.craftmaster.tz.main_screen.rest.response

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.rest.Success
import com.wimank.craftmaster.tz.common.room.entities.BaseEntity
import com.wimank.craftmaster.tz.common.room.entities.CategoryEntity

@JsonAdapter(CategoriesDeserializer::class)
data class CategoriesResponse<T : BaseEntity>(

    @SerializedName("success")
    var success: Success,

    @SerializedName("categoryList")
    var categoryList: List<T>

)