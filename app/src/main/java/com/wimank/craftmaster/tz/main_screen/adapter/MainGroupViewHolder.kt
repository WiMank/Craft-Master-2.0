package com.wimank.craftmaster.tz.main_screen.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.common.di.GlideApp
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.utils.MAIN_URL
import com.wimank.craftmaster.tz.main_screen.rest.response.GroupListItem
import kotlinx.android.synthetic.main.group_list_item.view.*

class MainGroupViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(mainGroupEntity: MainGroupEntity) {
        view.group_name.text = mainGroupEntity.group
        GlideApp
            .with(view)
            .load(mainGroupEntity.groupImage)
            .into(view.group_image)
    }
}