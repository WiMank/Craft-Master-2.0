package com.wimank.craftmaster.tz.main_screen.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.common.di.GlideApp
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.utils.IMAGE_FOLDER_NAME
import kotlinx.android.synthetic.main.group_list_item.view.*
import java.io.File

class MainGroupViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(mainGroupEntity: MainGroupEntity) {
        view.group_name.text = mainGroupEntity.group
        val targetImage = File(
            view.context.getExternalFilesDir(IMAGE_FOLDER_NAME),
            "${mainGroupEntity.groupImage}.png"
        )
        GlideApp
            .with(view)
            .load(targetImage)
            .into(view.group_image)
    }
}