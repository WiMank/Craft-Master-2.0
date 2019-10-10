package com.wimank.craftmaster.tz.main_screen.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.common.di.GlideApp
import com.wimank.craftmaster.tz.common.utils.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.main_screen.room.MainGroupEntity
import kotlinx.android.synthetic.main.simple_recycler_item.view.*
import java.io.File

class MainGroupViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        mainGroupEntity: MainGroupEntity,
        onItemClickListener: MainGroupAdapter.OnItemClickListener
    ) {
        view.item_name.text = mainGroupEntity.group

        val targetImage = File(
            view.context.getExternalFilesDir(IMAGE_FOLDER_NAME),
            "${mainGroupEntity.groupImage}.png"
        )

        GlideApp
            .with(view)
            .load(targetImage)
            .into(view.item_image)

        view.ll_rec.setOnClickListener {
            onItemClickListener.onItemClick(mainGroupEntity)
        }
    }
}
