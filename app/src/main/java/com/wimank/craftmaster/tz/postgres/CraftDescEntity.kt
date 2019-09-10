package com.wimank.craftmaster.tz.postgres

import com.google.gson.JsonObject
import com.wimank.craftmaster.tz.request.RecipePrimaryKey

data class CraftDescEntity(

    var recipePrimaryKey: RecipePrimaryKey,

    val recipe_name: JsonObject,

    var lleftParameter: JsonObject,

    var lleftParameterImage: String,

    var rrightParameter: String,

    var descriptionCraft: JsonObject,

    var wikiLink: String,

    var craftBluePrint: CraftBluePrintEntity
)

data class CraftBluePrintEntity(

    var recipePrimaryKey: RecipePrimaryKey,

    var firstSlot: String,

    var secondSlot: String,

    var threeSlot: String,

    var fourthSlot: String,

    var fifthSlot: String,

    var sixthSlot: String,

    var seventhSlot: String,

    var eighthSlot: String,

    var ninthSlot: String
)