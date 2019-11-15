package com.wimank.craftmaster.tz.app.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType

@Entity(tableName = "mobs")
data class MobsEntity(

    @PrimaryKey
    @ColumnInfo(name = "mob_icon")
    @SerializedName("mobIcon")
    val mobIcon: String,

    @ColumnInfo(name = "mob_name")
    @SerializedName("mobName")
    val mobName: LocalizedType,

    @ColumnInfo(name = "health")
    @SerializedName("health")
    val health: String,

    @ColumnInfo(name = "experience")
    @SerializedName("experience")
    val experience: String,

    @ColumnInfo(name = "type_mob")
    @SerializedName("typeMob")
    val typeMob: LocalizedType,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: LocalizedType,

    @ColumnInfo(name = "easy_attack")
    @SerializedName("easyAttack")
    val easyAttack: String,

    @ColumnInfo(name = "normal_attack")
    @SerializedName("normalAttack")
    val normalAttack: String,

    @ColumnInfo(name = "hard_attack")
    @SerializedName("hardAttack")
    val hardAttack: String,

    @ColumnInfo(name = "drop_one")
    @SerializedName("dropOne")
    val dropOne: String,

    @ColumnInfo(name = "drop_two")
    @SerializedName("dropTwo")
    val dropTwo: String,

    @ColumnInfo(name = "drop_three")
    @SerializedName("dropThree")
    val dropThree: String,

    @ColumnInfo(name = "drop_four")
    @SerializedName("dropFour")
    val dropFour: String,

    @ColumnInfo(name = "drop_five")
    @SerializedName("dropFive")
    val dropFive: String,

    @ColumnInfo(name = "drop_six")
    @SerializedName("dropSix")
    val dropSix: String,

    @ColumnInfo(name = "vers")
    @SerializedName("vers")
    val vers: Int

) : BaseEntity {
    override fun getVersion() = vers
    override fun getImage() = mobIcon
}
