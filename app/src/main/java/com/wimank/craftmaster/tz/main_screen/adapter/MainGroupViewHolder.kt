package com.wimank.craftmaster.tz.main_screen.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.common.di.GlideApp
import com.wimank.craftmaster.tz.common.utils.MAIN_URL
import com.wimank.craftmaster.tz.main_screen.rest.response.GroupListItem
import kotlinx.android.synthetic.main.group_list_item.view.*

class MainGroupViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(groupItem: GroupListItem) {
        view.group_name.text = groupItem.group

        GlideApp.with(view)
            .load("$MAIN_URL${groupItem.groupImage}")
            .centerCrop()
            .into(view.group_image)
    }
}