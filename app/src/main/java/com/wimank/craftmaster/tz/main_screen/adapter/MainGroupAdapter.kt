package com.wimank.craftmaster.tz.main_screen.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupDiffCallback
import com.wimank.craftmaster.tz.main_screen.mvp.models.UpdateListItem


class MainGroupAdapter(private var mList: List<MainGroupEntity>) :
    RecyclerView.Adapter<MainGroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainGroupViewHolder {
        return MainGroupViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.group_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainGroupViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun onBindViewHolder(holder: MainGroupViewHolder, position: Int, payloads: List<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            //val media = mVideos.get(position)
            for (data in payloads) {
                when (data as UpdateListItem) {
                    UpdateListItem.UPDATE ->{
                        Log.d("HHH", "UPDATE")
                    }
                    UpdateListItem.NOTHING_TO_UPDATE -> {
                        Log.d("HHH", "NOTHING_TO_UPDATE")
                    }
                }
            }
        }
    }

    fun update(newData: List<MainGroupEntity>) {
        val result = DiffUtil.calculateDiff(MainGroupDiffCallback(mList, newData), true)
        mList = newData
        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = mList.size
}