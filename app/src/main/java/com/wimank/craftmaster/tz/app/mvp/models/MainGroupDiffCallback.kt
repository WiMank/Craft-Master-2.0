package com.wimank.craftmaster.tz.app.mvp.models

import androidx.recyclerview.widget.DiffUtil
import com.wimank.craftmaster.tz.app.room.entitys.MainGroupEntity

class MainGroupDiffCallback(
    private val newList: List<MainGroupEntity>,
    private val oldList: List<MainGroupEntity>
) : DiffUtil.Callback() {

    override fun getNewListSize() = newList.size

    override fun getOldListSize() = oldList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].group == newList[newItemPosition].group
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].orderGroup == newList[newItemPosition].orderGroup
                &&
                oldList[oldItemPosition].groupImage == newList[newItemPosition].groupImage
                &&
                oldList[oldItemPosition].vers == newList[newItemPosition].vers
    }
}
