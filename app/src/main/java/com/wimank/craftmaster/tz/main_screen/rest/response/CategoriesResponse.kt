package com.wimank.craftmaster.tz.main_screen.rest.response

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(

    @field:SerializedName("categoriesList")
    val categoriesList: List<CategoriesListItem>
)

data class CategoriesListItem(

    @field:SerializedName("categoryImage")
    val categoryImage: String,

    @field:SerializedName("categoriesPrKey")
    val categoriesPrKey: CategoriesPrKey,

    @field:SerializedName("category")
    val category: Category,

    @field:SerializedName("categoryEndpoint")
    val categoryEndpoint: String
)

data class Category(

    @field:SerializedName("ru")
    val ru: String,

    @field:SerializedName("en")
    val en: String
)

data class CategoriesPrKey(

    @field:SerializedName("orderCategories")
    val orderCategories: String,

    @field:SerializedName("group")
    val group: String
)

