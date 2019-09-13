package com.wimank.craftmaster.tz.main_screen.adapter

import android.util.Base64
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wimank.craftmaster.tz.main_screen.rest.response.GroupListItem
import kotlinx.android.synthetic.main.group_list_item.view.*

class MainGroupViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(groupItem: GroupListItem) {
        view.group_name.text = groupItem.group
        Glide.with(view)
            .load(Base64.decode(groupItem.groupImage, Base64.DEFAULT))
            .centerCrop()
            .into(view.group_image)
    }
}