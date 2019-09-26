package com.wimank.craftmaster.tz.main_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupDiffCallback


class MainGroupAdapter(private var mList: ArrayList<MainGroupEntity>) :
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

    fun update(newList: ArrayList<MainGroupEntity>) {
        with(DiffUtil.calculateDiff(MainGroupDiffCallback(newList, mList))) {
            mList = newList
            dispatchUpdatesTo(this@MainGroupAdapter)
        }
    }

    override fun getItemCount() = mList.size
}