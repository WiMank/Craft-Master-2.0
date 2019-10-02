package com.wimank.craftmaster.tz.main_screen.rest.response

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.rest.Success
import com.wimank.craftmaster.tz.common.room.entities.BaseEntity

data class CategoryResponse<T : BaseEntity>(

    @SerializedName("success")
    val success: Success,

    @SerializedName("mcCategoryList")
    val mcCategoryList: List<T>
)