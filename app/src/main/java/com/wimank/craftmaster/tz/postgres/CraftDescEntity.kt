package com.wimank.craftmaster.tz.postgres

import com.wimank.craftmaster.tz.request.RecipePrimaryKey

data class CraftDescEntity(

    var recipePrimaryKey: RecipePrimaryKey,

    var lleftParameter: String,

    var lleftParameterImage: String,

    var rrightParameter: String,

    var descriptionCraft: String,

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