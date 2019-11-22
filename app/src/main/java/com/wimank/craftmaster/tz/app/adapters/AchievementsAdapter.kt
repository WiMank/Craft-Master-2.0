package com.wimank.craftmaster.tz.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.models.LocaleManager
import com.wimank.craftmaster.tz.app.room.entity.AchievementEntity

class AchievementsAdapter(
    private val mList: List<AchievementEntity>,
    private val mLocaleManager: LocaleManager
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
        holder.bind(mList[position], mLocaleManager)
    }

    override fun getItemCount() = mList.size

}
