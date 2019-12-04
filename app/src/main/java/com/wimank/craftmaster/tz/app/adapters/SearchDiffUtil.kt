package com.wimank.craftmaster.tz.app.adapters

import androidx.recyclerview.widget.DiffUtil
import com.wimank.craftmaster.tz.app.room.RecipesListItem

class SearchDiffUtil(
    private val oldList: List<RecipesListItem>,
    private val newList: List<RecipesListItem>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].attr == newList[newItemPosition].attr
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
                &&
                oldList[oldItemPosition].imageName == newList[newItemPosition].imageName
                &&
                oldList[oldItemPosition].section == newList[newItemPosition].section
    }
}
