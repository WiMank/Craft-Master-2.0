package com.wimank.craftmaster.tz.app.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.app.di.modules.GlideApp
import com.wimank.craftmaster.tz.app.mvp.common.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.app.room.LocalizedType
import com.wimank.craftmaster.tz.app.room.RecipesListItem
import com.wimank.craftmaster.tz.app.utils.getCurrentLocale
import kotlinx.android.synthetic.main.simple_recycler_item.view.*
import java.io.File

class RecipesListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        recipesListItem: RecipesListItem,
        onItemClickListener: RecipesListAdapter.OnItemClickListener
    ) {
        view.item_name.text = localizedName(view.context, recipesListItem.name)
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

    private fun localizedName(context: Context, name: LocalizedType): String {
        return when (getCurrentLocale(context).language) {
            "ru" -> name.getRuLocalization()
            "uk" -> name.getRuLocalization()
            else -> name.getEnLocalization()
        }
    }
}
