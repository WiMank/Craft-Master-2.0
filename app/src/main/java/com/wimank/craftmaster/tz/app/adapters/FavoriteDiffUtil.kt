package com.wimank.craftmaster.tz.app.adapters

import androidx.recyclerview.widget.DiffUtil
import com.wimank.craftmaster.tz.app.room.entity.FavoriteEntity

class FavoriteDiffUtil(
    private val oldList: List<FavoriteEntity>,
    private val newList: List<FavoriteEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].fRecipeAttr == newList[newItemPosition].fRecipeAttr
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].recipeImageName == newList[newItemPosition].recipeImageName
                &&
                oldList[oldItemPosition].recipeName == newList[newItemPosition].recipeName
    }
}
