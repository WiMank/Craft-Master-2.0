package com.wimank.craftmaster.tz.app.room

import android.os.Parcel
import android.os.Parcelable
import com.wimank.craftmaster.tz.app.rest.responses.RecipeName

data class RecipesListItem(
    val recipeName: RecipeName?,
    val recipeImageName: String?,
    val recipeAttr: String?,
    val modification: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(RecipeName::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(recipeName, flags)
        parcel.writeString(recipeImageName)
        parcel.writeString(recipeAttr)
        parcel.writeString(modification)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipesListItem> {
        override fun createFromParcel(parcel: Parcel): RecipesListItem {
            return RecipesListItem(parcel)
        }

        override fun newArray(size: Int): Array<RecipesListItem?> {
            return arrayOfNulls(size)
        }
    }
}
