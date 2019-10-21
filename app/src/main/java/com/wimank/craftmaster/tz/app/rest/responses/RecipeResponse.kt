package com.wimank.craftmaster.tz.app.rest.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.wimank.craftmaster.tz.app.room.RecipeDeserializer
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entitys.RecipeEntity

@JsonAdapter(RecipeDeserializer::class)
data class RecipeResponse(
    val success: Success,
    val descriptionList: List<DescriptionEntity>,
    val recipesList: List<RecipeEntity>
)

data class LeftParameter(
    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
)

data class LeftParameterText(
    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
)

data class RecipeName(
    @SerializedName("en")
    val en: String? = "",

    @SerializedName("ru")
    val ru: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(en)
        parcel.writeString(ru)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeName> {
        override fun createFromParcel(parcel: Parcel): RecipeName {
            return RecipeName(parcel)
        }

        override fun newArray(size: Int): Array<RecipeName?> {
            return arrayOfNulls(size)
        }
    }
}

data class DescriptionCraft(

    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
)

data class RightParameter(
    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
)

data class RightParameterText(
    @SerializedName("en")
    val en: String = "",

    @SerializedName("ru")
    val ru: String = ""
)
