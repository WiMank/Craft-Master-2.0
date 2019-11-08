package com.wimank.craftmaster.tz.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.room.entitys.AchievementEntity

class AchievementsAdapter(
    private var mList: List<AchievementEntity>
) : RecyclerView.Adapter<AchievementsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementsViewHolder {
        return AchievementsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.achiv_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AchievementsViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount() = mList.size

}
