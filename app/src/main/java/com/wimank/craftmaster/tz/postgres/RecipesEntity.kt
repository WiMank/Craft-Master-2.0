package com.wimank.craftmaster.tz.postgres

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CMrecipes")
data class RecipesEntity(

    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0,
    var name: String? = "def",
    var imageIcon: String? = "def",
    var imageResultCraft: String? = "def",
    var imageInstrument: String? = "def",
    var descriptionTextCraft: String? = "def",
    var wikiLink: String? = "def",
    var stackable: String? = "def",
    var idArray: String? = "def",
    var attackDamage: String? = "def",
    var durability: String? = "def",
    var armor: String? = "def",
    var machineName: String? = "def",
    var machine: String? = "def",
    var restores: String? = "def",
    var one: String? = "def",
    var two: String? = "def",
    var three: String? = "def",
    var four: String? = "def",
    var five: String? = "def",
    var six: String? = "def",
    var seven: String? = "def",
    var eight: String? = "def",
    var nine: String? = "def",

    var mobName: String? = "def",
    var mobIcon: String? = "def",
    var health: String? = "def",
    var exp: String? = "def",
    var typeMob: String? = "def",
    var descriptionMob: String? = "def",
    var easyAttack: String? = "def",
    var normalAttack: String? = "def",
    var hardAttack: String? = "def",
    var dropOne: String? = "def",
    var dropTwo: String? = "def",
    var dropThree: String? = "def",
    var dropFour: String? = "def",
    var dropFive: String? = "def",
    var dropSix: String? = "def",
    var furnace: String? = "def",
    var extractor: String? = "def",
    var crusher: String? = "def",
    var compressor: String? = "def",
    var recycler: String? = "def",
    var assemblyTable: String? = "def"
)