package com.wimank.craftmaster.tz.categories_screen.room

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.common.room.BaseEntity


@Entity(tableName = "categories", primaryKeys = ["group", "orderCategories"])
data class CategoryEntity(
    @SerializedName("group")
    var group: String,
    @SerializedName("orderCategories")
    var orderCategories: Int,
    @SerializedName("category")
    var category: Category,
    @SerializedName("categoryImage")
    var categoryImage: String,
    @SerializedName("categoryEndpoint")
    var categoryEndpoint: String,
    @SerializedName("vers")
    var vers: Int
) : BaseEntity {
    override fun getImage(): String = categoryImage
    override fun getVersion(): Int = vers
}

data class Category(
    @SerializedName("en")
    var en: String,

    @SerializedName("ru")
    var ru: String
)