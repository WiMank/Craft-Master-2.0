package com.wimank.craftmaster.tz.common.room.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "categories", primaryKeys = ["group", "orderCategories"])
data class CategoriesEntity(

    @SerializedName("group")
    val group: String,

    @SerializedName("orderCategories")
    val orderCategories: Int,

    @SerializedName("category")
    val category: Category,

    @SerializedName("categoryImage")
    val categoryImage: String,

    @SerializedName("categoryEndpoint")
    val categoryEndpoint: String,

    @SerializedName("vers")
    val vers: Int
)

data class Category(

    @SerializedName("ru")
    val ru: String,

    @SerializedName("en")
    val en: String
)