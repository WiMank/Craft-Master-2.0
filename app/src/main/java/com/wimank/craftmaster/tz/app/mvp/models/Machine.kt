package com.wimank.craftmaster.tz.app.mvp.models

data class Machine(
    val name: String,
    val image: String
) {
    fun nameNotEmpty() = name.isNotEmpty()
    fun imageNotEmpty() = image.isNotEmpty()
}
