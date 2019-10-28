package com.wimank.craftmaster.tz.app.room

import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType

data class RecipesListItem(
    val name: LocalizedType,
    val imageName: String,
    val attr: String,
    val section: String
)
