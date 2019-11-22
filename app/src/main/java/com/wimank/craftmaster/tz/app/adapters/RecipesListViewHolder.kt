package com.wimank.craftmaster.tz.app.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.app.di.modules.GlideApp
import com.wimank.craftmaster.tz.app.mvp.common.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.app.mvp.models.LocaleManager
import com.wimank.craftmaster.tz.app.room.RecipesListItem
import kotlinx.android.synthetic.main.simple_recycler_item.view.*
import java.io.File

class RecipesListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        recipesListItem: RecipesListItem,
        onItemClickListener: RecipesListAdapter.OnItemClickListener,
        localeManager: LocaleManager
    ) {
        view.item_name.text = localeManager.localizeString(recipesListItem.name)
        val targetImage = File(
            view.context.getExternalFilesDir(IMAGE_FOLDER_NAME),
            "${recipesListItem.imageName}.png"
        )

        GlideApp
            .with(view)
            .load(targetImage)
            .into(view.item_image)

        view.ll_rec.setOnClickListener {
            onItemClickListener.onItemClick(recipesListItem)
        }
    }
}
