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
        return localList[oldItemPosition] == serverList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return localList[oldItemPosition].group == serverList[newItemPosition].group
                &&
                localList[oldItemPosition].groupImage == serverList[newItemPosition].groupImage
                &&
                localList[oldItemPosition].endpoint == serverList[newItemPosition].endpoint
                &&
                localList[oldItemPosition].orderGroup == serverList[newItemPosition].orderGroup
                &&
                localList[oldItemPosition].vers == serverList[newItemPosition].vers
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
        return if (localList[oldItemPosition].vers < serverList[newItemPosition].vers)
            UpdateImage.UPDATE
        else
            UpdateImage.NOTHING_TO_UPDATE
    }
}