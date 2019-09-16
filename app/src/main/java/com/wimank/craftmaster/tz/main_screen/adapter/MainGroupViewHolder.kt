package com.wimank.craftmaster.tz.main_screen.adapter

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
            .addListener(listener)
            .into(view.group_image)
    }


    private val listener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

            return false
        }

        override fun onResourceReady(
            resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

            return false
        }
    }
}