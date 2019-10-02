package com.wimank.craftmaster.tz.common.room.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "mc_categories", primaryKeys = ["group", "orderCategories"])
data class McCategoryEntity(
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

@Entity(tableName = "ic_categories", primaryKeys = ["group", "orderCategories"])
data class IcCategoryEntity(
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

@Entity(tableName = "bc_categories", primaryKeys = ["group", "orderCategories"])
data class BcCategoryEntity(
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

@Entity(tableName = "fr_categories", primaryKeys = ["group", "orderCategories"])
data class FrCategoryEntity(
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