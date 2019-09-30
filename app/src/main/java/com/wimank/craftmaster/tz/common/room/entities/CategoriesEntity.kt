package com.wimank.craftmaster.tz.common.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "categories")
data class CategoriesEntity(

    @PrimaryKey
    @SerializedName("categoriesPrKey")
    val categoriesPrKey: CategoriesPrKey,

    @SerializedName("categoryImage")
    val categoryImage: String,

    @SerializedName("category")
    val category: Category,

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

data class CategoriesPrKey(

    @SerializedName("orderCategories")
    val orderCategories: String,

    @SerializedName("group")
    val group: String
)