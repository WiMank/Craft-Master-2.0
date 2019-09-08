package com.wimank.craftmaster.tz.postgres

import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.request.RecipePrimaryKey

data class CraftDescEntity(

    var recipePrimaryKey: RecipePrimaryKey,

    @SerializedName("lleft_parameter")
    var lleftParameter: String,

    @SerializedName("lleft_parameter_image")
    var lleftParameterImage: String,

    @SerializedName("rright_parameter")
    var rrightParameter: String,

    @SerializedName("description_craft")
    var descriptionCraft: String,

    @SerializedName("wiki_link")
    var wikiLink: String,

    var craftBluePrint: CraftBluePrintEntity
)

data class CraftBluePrintEntity(

    var recipePrimaryKey: RecipePrimaryKey,

    @SerializedName("first_slot")
    var firstSlot: String,

    @SerializedName("second_slot")
    var secondSlot: String,

    @SerializedName("three_slot")
    var threeSlot: String,

    @SerializedName("fourth_slot")
    var fourthSlot: String,

    @SerializedName("fifth_slot")
    var fifthSlot: String,

    @SerializedName("sixth_slot")
    var sixthSlot: String,

    @SerializedName("seventh_slot")
    var seventhSlot: String,

    @SerializedName("eighth_slot")
    var eighthSlot: String,

    @SerializedName("ninth_slot")
    var ninthSlot: String
)