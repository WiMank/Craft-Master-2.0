package com.wimank.craftmaster.tz.categories_screen.room

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.room.BaseEntity


@Entity(tableName = "categories", primaryKeys = ["group", "orderCategories"])
data class CategoryEntity(
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
) : BaseEntity {
    override fun getImage(): String = categoryImage
    override fun getVersion(): Int = vers
}

data class Category(
    @SerializedName("en")
    val en: String,

    @SerializedName("ru")
    val ru: String
)