package com.wimank.craftmaster.tz.app.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.app.di.modules.GlideApp
import com.wimank.craftmaster.tz.app.mvp.common.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.app.mvp.models.LocaleManager
import com.wimank.craftmaster.tz.app.room.entity.FavoriteEntity
import kotlinx.android.synthetic.main.simple_recycler_item.view.*
import java.io.File

class FavoriteViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        favoriteEntity: FavoriteEntity,
        onItemClickListener: FavoriteAdapter.OnItemClickListener,
        localeManager: LocaleManager
    ) {
        view.item_name.text = localeManager.localizeString(favoriteEntity.recipeName)
        val targetImage = File(
            view.context.getExternalFilesDir(IMAGE_FOLDER_NAME),
            "${favoriteEntity.recipeImageName}.png"
        )

        GlideApp
            .with(view)
            .load(targetImage)
            .into(view.item_image)

        view.ll_rec.setOnClickListener {
            onItemClickListener.onItemClick(favoriteEntity)
        }
    }
}
