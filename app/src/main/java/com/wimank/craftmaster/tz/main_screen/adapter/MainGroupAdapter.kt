package com.wimank.craftmaster.tz.main_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.main_screen.mvp.models.UpdateImage


class MainGroupAdapter(private val list: List<MainGroupEntity>) :
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
        holder.bind(list[position])
    }

   /* override fun onBindViewHolder(holder: MainGroupViewHolder, position: Int, payloads: List<Any>) {
        holder.bind(list[position])

        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            //val media = mVideos.get(position)
            for (data in payloads) {
                when (data as UpdateImage) {
                    UpdateImage.UPDATE ->{

                    }
                    UpdateImage.NOTHING_TO_UPDATE -> {

                    }
                }
            }
        }
    }*/

    override fun getItemCount() = list.size
}