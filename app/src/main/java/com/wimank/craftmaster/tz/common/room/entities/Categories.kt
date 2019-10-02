package com.wimank.craftmaster.tz.common.room.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "mc_categories", primaryKeys = ["group", "orderCategories"])
data class McCategoryEntity(
    var group: String,
    var orderCategories: Int,
    var category: Category,
    var categoryImage: String,
    var categoryEndpoint: String,
    var vers: Int
) : BaseEntity {
    override fun getImage(): String = categoryImage
    override fun getVersion(): Int = vers
}

@Entity(tableName = "ic_categories", primaryKeys = ["group", "orderCategories"])
data class IcCategoryEntity(
    var group: String,
    var orderCategories: Int,
    var category: Category,
    var categoryImage: String,
    var categoryEndpoint: String,
    var vers: Int
) : BaseEntity {
    override fun getImage(): String = categoryImage
    override fun getVersion(): Int = vers
}

@Entity(tableName = "bc_categories", primaryKeys = ["group", "orderCategories"])
data class BcCategoryEntity(
    var group: String,
    var orderCategories: Int,
    var category: Category,
    var categoryImage: String,
    var categoryEndpoint: String,
    var vers: Int
) : BaseEntity {
    override fun getImage(): String = categoryImage
    override fun getVersion(): Int = vers
}

@Entity(tableName = "fr_categories", primaryKeys = ["group", "orderCategories"])
data class FrCategoryEntity(
    var group: String,
    var orderCategories: Int,
    var category: Category,
    var categoryImage: String,
    var categoryEndpoint: String,
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