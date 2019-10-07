package com.wimank.craftmaster.tz.categories_screen.rest

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.rest.Success
import com.wimank.craftmaster.tz.common.room.BaseEntity

data class CategoryResponse<T : BaseEntity>(

    @SerializedName("success")
    val success: Success,

    @SerializedName("mcCategoryList")
    val mcCategoryList: List<T>
)