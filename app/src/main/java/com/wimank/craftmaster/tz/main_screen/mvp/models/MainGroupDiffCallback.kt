package com.wimank.craftmaster.tz.main_screen.mvp.models

import androidx.recyclerview.widget.DiffUtil
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity

class MainGroupDiffCallback(
    private val serverList: List<MainGroupEntity>,
    private val localList: List<MainGroupEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize() = localList.size

    override fun getNewListSize() = serverList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return localList[oldItemPosition].group == serverList[newItemPosition].group
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return localList[oldItemPosition].vers == serverList[newItemPosition].vers
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
        return if (localList[oldItemPosition].vers < serverList[newItemPosition].vers)
            UpdateListItem.UPDATE
        else
            UpdateListItem.NOTHING_TO_UPDATE
    }
}