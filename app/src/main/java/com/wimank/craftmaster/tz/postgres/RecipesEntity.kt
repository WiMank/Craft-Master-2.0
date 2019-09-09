package com.wimank.craftmaster.tz.postgres

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CMrecipes")
data class RecipesEntity(

    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0,
    var name: String? = "",
    var imageIcon: String? = "",
    var imageResultCraft: String? = "",
    var imageInstrument: String? = "",
    var descriptionTextCraft: String? = "",
    var wikiLink: String? = "",
    var stackable: String? = "",
    var idArray: String? = "",
    var attackDamage: String? = "",
    var durability: String? = "",
    var armor: String? = "",
    var machineName: String? = "",
    var machine: String? = "",
    var restores: String? = "",
    var one: String? = "",
    var two: String? = "",
    var three: String? = "",
    var four: String? = "",
    var five: String? = "",
    var six: String? = "",
    var seven: String? = "",
    var eight: String? = "",
    var nine: String? = "",

    var mobName: String? = "",
    var mobIcon: String? = "",
    var health: String? = "",
    var exp: String? = "",
    var typeMob: String? = "",
    var descriptionMob: String? = "",
    var easyAttack: String? = "",
    var normalAttack: String? = "",
    var hardAttack: String? = "",
    var dropOne: String? = "",
    var dropTwo: String? = "",
    var dropThree: String? = "",
    var dropFour: String? = "",
    var dropFive: String? = "",
    var dropSix: String? = "",
    var furnace: String? = "",
    var extractor: String? = "",
    var crusher: String? = "",
    var compressor: String? = "",
    var recycler: String? = "",
    var assemblyTable: String? = ""
)