package com.wimank.craftmaster.tz.main_screen.mvp.models

import androidx.recyclerview.widget.DiffUtil
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity

class MainGroupDiffCallback(
    private val newList: List<MainGroupEntity>,
    private val oldList: List<MainGroupEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].group == newList[newItemPosition].group
                &&
                oldList[oldItemPosition].orderGroup == newList[newItemPosition].orderGroup
                &&
                oldList[oldItemPosition].endpoint == newList[newItemPosition].endpoint
                &&
                oldList[oldItemPosition].groupImage == newList[newItemPosition].groupImage

    }
}